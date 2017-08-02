


$.post( "api/getProjectTree", { name: "John" })
.done(function( data ) {

    var html = "<ul>";

    $.each( data, function( key, value ) {
        if(value.childCount > 0){
            html += "<li><span class='expand' data-folderid='0'>+</span>"+value.name+" <span class='child'>d</span></li>";
        } else{
            html += "<li>"+value.name+"</li>";
        }

    });



    html += "</ul>";



    console.log(data);


    $("#projectTree").html(html);
//alert( "Data Loaded: " + data );
});



$( "#projectTree" ).on( "click",".expand", function() {
    console.log("clicked");

    var fId = $( this).data( "folderid" );


    console.log(fId);


    var childDiv = $( this).next();

    var html = "<ul>";

    $.post( "api/getFolderTree", { projectId: "1", folderId : fId }).done(function( data ) {


        $.each( data, function( key, value ) {
            if(value.childCount > 0){
                html += "<li><span class='expand' data-folderID='"+value.id+"'>+</span>"+value.name+" <span class='child'>d</span></li>";
            } else{
                html += "<li>"+value.name+"</li>";
            }

        });

        html += "</ul>";


        childDiv.html(html);

        console.log(data);

    });


});



//int userId = 1;