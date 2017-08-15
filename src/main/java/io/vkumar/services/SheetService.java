package io.vkumar.services;



import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.vkumar.Helpers.FormValidation;
import io.vkumar.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.spel.ast.FormatHelper;
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


    public DatatableResponse getJimView(DatatableRequest datatableRequest) {

        int sheetId = 1;
        int filteredRows = 0;

        String cols[] = {"name","comment","date_of_birth","gender","salary", "last_seen_time", "category"};
        String searhString = datatableRequest.getSearch().get("value").trim();
        int colOrder = Integer.parseInt(datatableRequest.getOrder().get(0).get("column"));
        String colOrderName = cols[colOrder];
        String colOrderDir = datatableRequest.getOrder().get(0).get("dir");

        //total
        String totalSql = "SELECT COUNT(DISTINCT record_id) from sheet_data WHERE sheet_id = ?";
        int totalRows = jdbcTemplate.queryForObject(totalSql,new Object[] { sheetId }, Integer.class);

        //build query
        StringBuilder builder = new StringBuilder();
        StringBuilder filtredSql = new StringBuilder();

        builder.append("SELECT record_id, group_concat(name, '::', value SEPARATOR '\n') AS data FROM sheet_data WHERE sheet_id = 1");

        if(!searhString.isEmpty()){
            builder.append(" AND record_id IN (SELECT record_id from sheet_data WHERE value Like '%"+searhString+"%') ");
            filtredSql.append("SELECT COUNT(DISTINCT record_id) from sheet_data WHERE sheet_id = ? AND value Like '%"+searhString+"%'");
            filteredRows = jdbcTemplate.queryForObject(filtredSql.toString(),new Object[] { sheetId }, Integer.class);
        } else{
            filteredRows = totalRows;
        }

        builder.append(" GROUP BY record_id ");

        if(!colOrderName.isEmpty()){
            builder.append( " ORDER BY "+ colOrderName + " "+ colOrderDir);
        }

        builder.append(" LIMIT "+datatableRequest.getStart() + ","+ datatableRequest.getLength());

        System.out.println(builder.toString());

        List<Map<String, Object>> ls = jdbcTemplate.queryForList(builder.toString());

        List<Map<String, Object>> items = new ArrayList();

        Map<String, Object> test = new HashMap<String, Object>();





        for(Map<String, Object> record:ls ){
            String data = (String) record.get("data");

            String[] keyValecols = data.split("\\r?\\n");

            for (String keyValecol : keyValecols){
                test.put(keyValecol.split("::")[0], keyValecol.split("::")[1]);
            }

            items.add(test);

           // System.out.println();

        }






        //SELECT record_id, group_concat(name, '=', value) from sheet_data WHERE sheet_id = 1 group by record_id



        DatatableResponse datatableResponse = new DatatableResponse();
        datatableResponse.setDraw(datatableRequest.getDraw());
        datatableResponse.setRecordsTotal(totalRows);
        datatableResponse.setRecordsFiltered(filteredRows);
        datatableResponse.setData(items);

        return datatableResponse;




    }

    public DatatableResponse getView(DatatableRequest datatableRequest) {


        int sheetId = 1;
        int filteredRows = 0;

       // String totalSql = "SELECT count(*) FROM ( SELECT COUNT(*) AS count FROM sheet_data WHERE sheet_id = ? GROUP BY record_id) AS total";

        String totalSql = "SELECT COUNT(DISTINCT record_id) from sheet_data WHERE sheet_id = ?";


        int totalRows = jdbcTemplate.queryForObject(totalSql,new Object[] { sheetId }, Integer.class);


        String cols[] = {"name","comment","date_of_birth","gender","salary", "last_seen_time", "category"};
        String searhString = datatableRequest.getSearch().get("value").trim();
        int colOrder = Integer.parseInt(datatableRequest.getOrder().get(0).get("column"));
        String colOrderName = cols[colOrder];
        String colOrderDir = datatableRequest.getOrder().get(0).get("dir");

        //build query
        StringBuilder builder = new StringBuilder();
        StringBuilder filtredSql = new StringBuilder();
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
            filtredSql.append("SELECT COUNT(DISTINCT record_id) from sheet_data WHERE sheet_id = ? AND value Like '%"+searhString+"%'");
            filteredRows = jdbcTemplate.queryForObject(filtredSql.toString(),new Object[] { sheetId }, Integer.class);

        } else{
            filteredRows = totalRows;
        }

        builder.append(" GROUP BY record_id ");


        if(!colOrderName.isEmpty()){
            builder.append( " ORDER BY "+ colOrderName + " "+ colOrderDir);
        }

        builder.append(" LIMIT "+datatableRequest.getStart() + ","+ datatableRequest.getLength());

        //System.out.println(builder.toString());


        List<Map<String, Object>> ls = jdbcTemplate.queryForList(builder.toString());


        DatatableResponse datatableResponse = new DatatableResponse();
        datatableResponse.setDraw(datatableRequest.getDraw());
        datatableResponse.setRecordsTotal(totalRows);
        datatableResponse.setRecordsFiltered(filteredRows);
        datatableResponse.setData(ls);

        return datatableResponse;


    }

    public List<MetaData> getSheetHeader() {

        int sheetId = 1;

        String sqlString = "SELECT * FROM meta_data WHERE sheet_id = ?";

        List<MetaData> metaData = jdbcTemplate.query(sqlString,new Object[] { sheetId }, new BeanPropertyRowMapper<MetaData>(MetaData.class));

        return metaData;
    }

    public Boolean saveSheet(int rowId, String colId, String newVal) {

        int sheetId = 1;

        //get validation rule here
        String metaSql = "SELECT validations FROM meta_data WHERE sheet_id=? AND col_name=?";

        MetaData metaData = jdbcTemplate.queryForObject(metaSql, new Object[] { sheetId, colId }, new BeanPropertyRowMapper<MetaData>(MetaData.class));

        //System.out.println(metaData.getValidations());

        FormValidation.validate(newVal, metaData.getValidations());


        String SQL = "UPDATE sheet_data SET value = ? WHERE sheet_id=? AND record_id = ? AND name=?";
        jdbcTemplate.update(SQL, newVal,sheetId,rowId,colId );

        return true;


    }

    public List<Option> getOptions(String optionKey) {

        String sqlString = "SELECT * FROM options WHERE name = ?";

        return jdbcTemplate.query(sqlString,new Object[] { optionKey }, new BeanPropertyRowMapper<Option>(Option.class));

    }

    public SelectResponse getJoinCol(int sheetId,String colName,String query) {

        String sqlString = "SELECT value as id,value as text FROM sheet_data WHERE sheet_id = ? AND name = ? AND value LIKE '%"+query+"%' LIMIT 10";

       // return jdbcTemplate.query(sqlString,new Object[] { sheetId,colName }, new BeanPropertyRowMapper<Option>(Option.class));

        List<SelectOption> results = jdbcTemplate.query(sqlString,new Object[] { sheetId,colName }, new BeanPropertyRowMapper<SelectOption>(SelectOption.class));

        SelectResponse selectResponse = new SelectResponse();
        selectResponse.setItems(results);
        selectResponse.setTotal(results.size());
        return selectResponse;
    }
}
