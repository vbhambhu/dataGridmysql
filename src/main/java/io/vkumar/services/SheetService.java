package io.vkumar.services;



import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.vkumar.entities.DatatableRequest;
import io.vkumar.entities.DatatableResponse;
import io.vkumar.entities.MetaData;
import io.vkumar.entities.SheetData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SheetService {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public DatatableResponse getView(DatatableRequest datatableRequest) {


        int sheetId = 1;

        String totalSql = "SELECT count(*) FROM ( SELECT COUNT(*) AS count FROM sheet_data WHERE sheet_id = ? GROUP BY record_id) AS total";
        int totalRows = jdbcTemplate.queryForObject(totalSql,new Object[] { sheetId }, Integer.class);


        String cols[] = {"name","comment","date_of_birth","gender","salary", "last_seen_time", "category"};


        String searhString = datatableRequest.getSearch().get("value").trim();
        int colOrder = Integer.parseInt(datatableRequest.getOrder().get(0).get("column"));
        String colOrderName = cols[colOrder];
        String colOrderDir = datatableRequest.getOrder().get(0).get("dir");

        //build query
        StringBuilder builder = new StringBuilder();
        builder.append("SELECT ");

        for(String col : cols){
            builder.append("MAX(CASE WHEN name='");
            builder.append(col);
            builder.append("' THEN value END) AS ");
            builder.append(col);
            builder.append(",");
        }

        builder.append("record_id");
        builder.append(" FROM sheet_data WHERE sheet_id = 1 ");

        if(!searhString.isEmpty()){
            builder.append(" AND record_id IN (SELECT record_id from sheet_data WHERE value Like '%"+searhString+"%') ");
        }

        builder.append(" GROUP BY record_id ");

       // int filteredRows = jdbcTemplate.queryForObject(builder.toString(),new Object[] { sheetId }, Integer.class);

        if(!colOrderName.isEmpty()){
            builder.append( " ORDER BY "+ colOrderName + " "+ colOrderDir);
        }


        builder.append(" LIMIT "+datatableRequest.getStart() + ","+ datatableRequest.getLength());




        List<Map<String, Object>> ls = jdbcTemplate.queryForList(builder.toString());


        DatatableResponse datatableResponse = new DatatableResponse();
        datatableResponse.setDraw(datatableRequest.getDraw());
        datatableResponse.setRecordsTotal(totalRows);
        datatableResponse.setRecordsFiltered(1021);
        datatableResponse.setData(ls);

        return datatableResponse;


    }

    public List<MetaData> getSheetHeader() {

        int sheetId = 1;

        String sqlString = "SELECT * FROM meta_data WHERE sheet_id = ?";

        List<MetaData> metaData = jdbcTemplate.query(sqlString,new Object[] { sheetId }, new BeanPropertyRowMapper<MetaData>(MetaData.class));

        return metaData;
    }
}
