var tableName= '#datatable';
var columns = new Array();

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



    //console.log(columns);

    var dtable = $(tableName).DataTable( {
        "processing": true,
        "serverSide": true,
        "lengthMenu": [[10,15, 25, 50], [10,15, 25, 50]],
        "pageLength": 10,
        "ajax":{
            "url": "api/viewDatatable",
            "type": "POST",
            // "success": function(f){
            //     console.log(f);
            // }
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
                "visible": false,
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
        }

        $('.cellEdit').focus();

    });




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