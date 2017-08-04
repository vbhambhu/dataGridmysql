var tableName= '#datatable';
var columns = new Array();

$.post( "api/getHeaders", function( data ) {


    $.each(data, function (k, colObj) {
        str = '<th>' + colObj.colLabel + '</th>';
        $(str).appendTo(tableName+'>thead>tr');
        columns.push({"data": colObj.colName});
    });

}).done(function( data ) {

    console.log("=====DT=======");

    var dtable = $(tableName).DataTable( {
        "processing": true,
        "serverSide": true,
        "lengthMenu": [[15, 25, 50], [15, 25, 50,]],
        "pageLength": 15,
        //"rowId": 'staffId',
        "ajax":{
            "url": "api/viewDatatable",
            "type": "POST"
        },
        "columns": columns,
        'createdRow': function( row, data, dataIndex ) {
            //$(row).attr('id', data[0]);
        },
        "columnDefs": [{
            "className": "tblCell",
            "targets": '_all',
            'createdCell':  function (td, cellData, rowData, row, col) {
                //$(td).attr('id', col);
            }
        },
            {
               // "targets": [ 0 ],
               // "visible": true,
               // "searchable": true
            }
        ],
        "initComplete": function(settings, json) {

            //console.log(settings);
            //initPlugins();
        }
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