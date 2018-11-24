$(function() {

    $.each(Resource_Province, function(index, item) {
    	//console.log(item);
        $('.province').append('<option value="' + item.key + '">' + item.val + '</option>');
        $('.province2').append('<option value="' + item.key + '">' + item.val + '</option>');
    });
    $('.province').change(function() {
        var pKey = $(this).val();
        var cityArray = GetCitiesByProvinceKey(pKey);
        $('.City option:gt(0)').remove();
        $.each(cityArray, function(index, item) {
            $('.City').append('<option value="' + item.val + '">' + item.val + '</option>');
        });
    });
 $('.province2').change(function() {
        var pKey = $(this).val();
        var cityArray = GetCitiesByProvinceKey(pKey);
        $('.City2 option:gt(0)').remove();
        $.each(cityArray, function(index, item) {
            $('.City2').append('<option value="' + item.val + '">' + item.val + '</option>');
        });
    });
    
    });