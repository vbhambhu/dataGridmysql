package io.vkumar.services;



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

@Service
public class SheetService {


    @Autowired
    JdbcTemplate jdbcTemplate;

    public DatatableResponse getView(DatatableRequest datatableRequest) {

        int sheetId = 1;

        DatatableResponse datatableResponse = new DatatableResponse();

        String sqlString = "SELECT id, group_concat(VALUE) from sheet_data WHERE sheet_id = ? group by record_id";

        List<SheetData> records = jdbcTemplate.query(sqlString,new Object[] { sheetId }, new BeanPropertyRowMapper<SheetData>(SheetData.class));

        for(SheetData record : records){

            System.out.println(record.getName());
            //column.put("title",metaRow.getCol_name() );
        }




        return datatableResponse;
    }

    public String[] getSheetHeader() {

        int sheetId = 1;

        String sqlString = "SELECT * FROM meta_data WHERE sheet_id = ?";

        List<MetaData> metaData = jdbcTemplate.query(sqlString,new Object[] { sheetId }, new BeanPropertyRowMapper<MetaData>(MetaData.class));


        String cols[] = {"dddd","fffff","ccc"};

        for(MetaData metaRow : metaData){

            // System.out.println(metaRow.getCol_name());
            //column.put("title",metaRow.getCol_name() );
        }

        return cols;
    }
}
