// JavaScript Document
$(document).ready(function() {
 $.get('province.xml', function(data) {
    $(data).find('province').each(function() {
     var $province = $(this);
     var $option_province = $('<option></option>');
     $option_province.attr('value', $province.attr('name'))
       .append($province.attr('name'))
       .appendTo('#province');
    });
   });
 $('#province').change(function() {
  if ($('#province').val() == '') {
   $('#city').empty().append('<option>请选择城市：</option>');
   $('#town').empty().append('<option>请选择县/区：</option>');
  } else {
   $.get('province.xml', function(data) {
      $(data).find('province').each(function() {
       if ($(this).attr('name') == $('#province').val()) {
        $('#city').empty();
        var $select = $('<option>请选择城市：</option>');
        $('#town').empty()
          .append('<option>请选择县/区：</option>');
        $select.attr('value', '').appendTo('#city');
        $(this).find('city').each(function() {
         var $option_city = $('<option></option>');
         $option_city.attr('value',
           $(this).attr('name'))
           .append($(this).attr('name'))
           .appendTo('#city');
        });
       }
      });
     });
  }
 });
 $('#city').change(function() {
  if ($('#city').val() == '') {
   $('#town').empty().append('<option>请选择县/区：</option>');
  } else {
   $.get('province.xml', function(data) {
      $(data).find('city').each(function() {
       if ($(this).attr('name') == $('#city').val()) {
        $('#town').empty();
        var $select = $('<option>请选择县/区：</option>');
        $select.attr('value', '').appendTo('#town');
        if ($(this).find('town').length != 0) {
         $(this).find('town').each(function() {
          var $option_town = $('<option></option>');
          $option_town.attr('value',
            $(this).text()).append($(this)
            .text()).appendTo('#town');
         });
        }
       }
      });
     });
  }
 });
});

