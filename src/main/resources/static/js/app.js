var tableName= '#datatable';
var columns = new Array();



// $( ".testSelect" ).select2({
//     ajax: {
//         url: "/api/getJoin",
//         dataType: 'json',
//         delay: 250,
//         type: "POST",
//         data: function (params) {
//             return {
//                 q: params.term, // search term
//                 sid: 1,
//                 col: 'name',
//             };
//         },
//         processResults: function (data) {
//             // parse the results into the format expected by Select2.
//             // since we are using custom formatting functions we do not need to
//             // alter the remote JSON data
//             console.log(data);
//             return {
//                 results: data.data
//             };
//         },
//         cache: true
//     },
//     templateResult: function (data) {
//         if (data.loading) return data.name;
//
//
//         return data.name;
//     },
//     templateSelection: function (data) {
//         return data.name || "dadadadadadadadadas da";
//     },
//     escapeMarkup: function (m) {
//         return m;
//     },
//     minimumInputLength: 2
// });



//
// function formatRepo (repo) {
//     var markup = repo.value;;
//     return markup;
// }
//
// function formatRepoSelection (repo) {
//     return repo.value || repo.value;
// }





$.post( "api/getHeaders", function( data ) {

    //first col
    columns.push({"data": "record_id"});
    str = '<th>#</th>';
    $(str).appendTo(tableName+'>thead>tr');

    $.each(data, function (k, colObj) {
        str = '<th data-itype="'+colObj.inputType+'">' + colObj.colLabel + '</th>';
        $(str).appendTo(tableName+'>thead>tr');
        columns.push({"data": colObj.colName});
    });

}).done(function( dataa ) {

    console.log("=====DT=======");
    var dtable = $(tableName).DataTable( {
        "processing": true,
        "serverSide": true,
        "lengthMenu": [[10,15, 25, 50], [10,15, 25, 50]],
        "pageLength": 10,
        "ajax":{
            "url": "api/viewDatatable",
            "type": "POST"
        },
        "columns": columns,
        'createdRow': function( row, data, dataIndex ) {
            $(row).attr('id', data.record_id);
        },
        "columnDefs": [{
            "className": "tblCell",
            "targets": '_all',
            'createdCell':  function (td, cellData, rowData, row, col) {
                $(td).attr('rId', rowData.record_id);
                $(td).attr('colId', columns[col].data);
            }
        },
            {
               "targets": [ 0 ],
                "visible": true,
                "searchable": false
            }
        ],
        "initComplete": function(settings, json) {

            //console.log(settings);
            //initPlugins();
        }
    });


    $('#datatable').on( 'dblclick', 'td', function () {

        var $td = $(this);
        var currentVal = $td.text();
        var $th = $('#datatable').find('th').eq(parseInt($td.index()));
        var inputType = $th.data('itype');
        var html = '';

        if(inputType == 'text'){
            html += '<input type="text" value="'+currentVal+'"  class="cellEdit">';
            $(this).html(html);
        } else if(inputType == 'textarea'){
            html += '<textarea class="cellEdit hidden">'+currentVal+'</textarea>';
            $(this).html(html);
        } else if(inputType == 'date'){
            html += '<input type="text" value="'+currentVal+'"   class="datepicker">';
            $(this).html(html);
            $( ".datepicker" ).datepicker({
                dateFormat: 'dd-mm-yy',
                onClose: function () {
                    saveData(this);
                }
            }).focus();
        } else if(inputType.indexOf('select') !== -1){
            html += '<select class="optionSelect" style="width:100%;"></select>';
            $(this).html(html);
            initSelect(inputType,currentVal);
        }  else if(inputType.indexOf('join') !== -1){
            html += '<select class="joinSelect form-control" ></select>';
            $(this).html(html);
            initJoinSelect(inputType);
        } else if(inputType.indexOf('api') !== -1){
            html += '<select class="apiSelect form-control"></select>';
            $(this).html(html);
            initapiSelect(inputType);
        }

        $('.cellEdit').focus();

    });



    $('#datatable').on('change', '.optionSelect', function (e) {
        saveData(this);
    });

    $('#datatable').on('blur', '.cellEdit', function (e) {
        saveData(this);
    });


    function  saveData($elem) {
        var newVal = $($elem).val();
        var $td = $($elem).closest('td');
        var rowId = parseInt($td.attr('rid'));
        var colId = $td.attr('colid');

        //Save value here
        $.post( "api/sheet/save", { rowId:rowId , colId: colId, newVal: newVal } , function( response ) {
            $($elem).remove();
            $td.text(newVal);
            console.log(response);
            if(response.status){
                $.notify(response.msg, "success");
            } else{
                $.notify(response.msg, "error");
            }

        });

    }

    function initSelect(input,currentVal){

        var option_key = input.split("_")[1];
        $.post( "api/getOptions", { optionKey:option_key } , function( response ) {
            $.each(response, function(index, option) {
                $('.optionSelect').append( $('<option></option>').val(option.value).html(option.value) );
            });
            $(".optionSelect").val(currentVal).select2();
        });


    }

    function initapiSelect(input){

        var option = input.split("_");

        $(".apiSelect").select2({
            ajax: {
                url: "/api/"+ option[1],
                dataType: 'json',
                delay: 250,
                data: function (params) {
                    return {
                        q: params.term,
                        page: params.page || 1
                    };
                },
                processResults: function (data, params) {
                    params.page = params.page || 1;
                    return {
                        results: data.items,
                        pagination: {
                            more: (params.page * 30) < data.total_count
                        }
                    };
                },
                cache: true
            },
            escapeMarkup: function (markup) { return markup; }, // let our custom formatter work
            minimumInputLength: 1,
            templateResult: formatRepo, // omitted for brevity, see the source of this page
            templateSelection: formatRepoSelection // omitted for brevity, see the source of this page
        });
    }

    function formatRepo (repo) {
        return repo.text;
    }

    function formatRepoSelection (repo) {
        return repo.text;
    }


    function initJoinSelect(input){

        var option = input.split("_");

        $(".joinSelect").select2({
        ajax: {
            url: "/api/getJoin",
            dataType: 'json',
            delay: 250,
            type: "POST",
            data: function (params) {
                return {
                    q: params.term,
                    page: params.page || 1,
                    sid: option[1],
                    col: option[2],
                };
            },
            processResults: function (data, params) {
                console.log(data);
                params.page = params.page || 1;
                return {
                    results: data.items,
                    pagination: {
                        more: (params.page * 30) < data.total
                    }
                };
            },
            cache: true
        },
        escapeMarkup: function (markup) { return markup; }, // let our custom formatter work
        minimumInputLength: 1,
        templateResult: formatRepo, // omitted for brevity, see the source of this page
        templateSelection: formatRepoSelection // omitted for brevity, see the source of this page
    });


    }




});





$.post( "api/getProjectTree", { name: "John" })
.done(function( data ) {

    var html = "<ul>";

    $.each( data, function( key, value ) {
        if(value.childCount > 0){
            html += "<li><i class='fa fa-plus-square expandProject' data-folderid='0'></i> <i class='fa fa-archive'></i> "+value.name+" <span class='child'></span></li>";
        } else{
            html += "<li><i class='fa fa-archive'></i> "+value.name+"</li>";
        }
    });

    html += "</ul>";
    $("#projectTree").html(html);
});



$( "#projectTree" ).on( "click",".expandProject", function() {

    var fId = $( this).data( "folderid" );
    var childDiv = $( this).next();
    var html = "<ul>";

    $.post( "api/getFolderTree", { projectId: "1", folderId : fId }).done(function( data ) {

        $.each( data, function( key, value ) {
            if(value.childCount > 0){
                html += "<li><span class='expand' data-folderID='"+value.id+"'><i class='fa fa-archive'></i></span> "+value.name+" <span class='child'></span></li>";
            } else{
                html += "<li>"+value.name+"</li>";
            }

        });

        html += "</ul>";
        childDiv.html(html);

    });


});



//int userId = 1;


/*
*
*
*

 $('#datatable').on( 'dblclick', 'td', function () {

 var currentVal = $(this).text();
 var $th = $('#datatable').find('th').eq(parseInt($(this).attr('id')) -1);
 var inputType = $th.data('input_type');
 var html = '';

 if(inputType == 'text'){
 html += '<input type="text" value="'+currentVal+'"  class="cellEdit">';
 $(this).html(html);
 } else if(inputType == 'date'){
 html += '<input type="text" value="'+currentVal+'"   class="datepicker">';
 $(this).html(html);
 $( ".datepicker" ).datepicker({dateFormat: 'dd-mm-yy'}).focus();
 } else if(inputType == 'textarea'){
 html += '<textarea class="cellEdit hidden">'+currentVal+'</textarea>';
 $(this).html(html);
 } else if(inputType.indexOf('select') !== -1){
 html += '<select class="optionSelect"></select>';
 $(this).html(html);
 initSelect(inputType);
 } else if(inputType.indexOf('join') !== -1){
 html += '<select class="joinSelect form-control"></select>';
 $(this).html(html);
 initJoinSelect(inputType);
 } else if(inputType.indexOf('api') !== -1){
 html += '<select class="apiSelect form-control"></select>';
 $(this).html(html);
 initapiSelect(inputType);
 }


 //initPlugins();
 $('.cellEdit').focus();

 });


 $('#datatable').on('blur', '.cellEdit', function (e) {

 var newValue = $(this).val();
 var $tr = $(this).closest('tr');
 var $td = $(this).closest('td');
 var rowId = $tr.attr('id');

 var $thMeta = $('#datatable').find('th').eq(parseInt($td.attr('id')) -1);
 var col = $thMeta.data('input_name');

 //Save value here
 console.log(col);
 $.post( "datatable/save", { id:rowId , col: col, newVal: newValue } , function( response ) {
 console.log(response)
 });


 $(this).remove();
 $td.text(newValue);
 });

 function initSelect(input){

 var option_key = input.split("_")[1];

 $.post( "api/get_options", { option_key:option_key } , function( response ) {
 $.each(response, function(index, option) {
 $('.optionSelect').append( $('<option></option>').val(option.option_value).html(option.option_value) );
 });
 });
 }


 function initJoinSelect(input){

 var option = input.split("_");

 $(".joinSelect").select2({
 ajax: {
 url: "/api/get_join",
 dataType: 'json',
 delay: 250,
 data: function (params) {
 return {
 q: params.term,
 page: params.page || 1,
 tbl: option[1],
 col: option[2],
 };
 },
 processResults: function (data, params) {
 params.page = params.page || 1;
 return {
 results: data.items,
 pagination: {
 more: (params.page * 30) < data.total_count
 }
 };
 },
 cache: true
 },
 escapeMarkup: function (markup) { return markup; }, // let our custom formatter work
 minimumInputLength: 1,
 templateResult: formatRepo, // omitted for brevity, see the source of this page
 templateSelection: formatRepoSelection // omitted for brevity, see the source of this page
 });


 }

 function formatRepo (repo) {
 var markup = repo.name;;
 return markup;
 }

 function formatRepoSelection (repo) {
 return repo.name || repo.name;
 }




 function initapiSelect(input){

 var option = input.split("_");

 $(".apiSelect").select2({
 ajax: {
 url: "/api/"+option[1],
 dataType: 'json',
 delay: 250,
 data: function (params) {
 return {
 q: params.term,
 page: params.page || 1
 };
 },
 processResults: function (data, params) {
 params.page = params.page || 1;
 return {
 results: data.items,
 pagination: {
 more: (params.page * 30) < data.total_count
 }
 };
 },
 cache: true
 },
 escapeMarkup: function (markup) { return markup; }, // let our custom formatter work
 minimumInputLength: 1,
 templateResult: formatRepo, // omitted for brevity, see the source of this page
 templateSelection: formatRepoSelection // omitted for brevity, see the source of this page
 });


 }


 */