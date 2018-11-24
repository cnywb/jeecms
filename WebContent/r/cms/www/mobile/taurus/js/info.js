$(function() {
    var btn_active = 0;

    $.each(Resource_Province, function(index, item) {
    	//console.log(item);
        $('#provinceDDL').append('<option value="' + item.key + '">' + item.val + '</option>');
    });
    $('#provinceDDL').change(function() {
        var pKey = $(this).val();
        var cityArray = GetCitiesByProvinceKey(pKey);
        $('#cityDDL option:gt(0)').remove();
        $.each(cityArray, function(index, item) {
            $('#cityDDL').append('<option value="' + item.val + '">' + item.val + '</option>');
        });
    });
    
    });