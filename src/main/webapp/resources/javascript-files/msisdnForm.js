$body = $("body");

$(document).on({
    ajaxStart: function() { $body.addClass("loading");    },
     ajaxStop: function() { $body.removeClass("loading"); }    
});

$('#langlist').on('change', function() {
	lang = $('#langlist').val() == 'ar' ? 'ar' : 'en';
	var url_string = window.location.href;
	var url = new URL(url_string);
	window.location.assign("./?lang=" + lang);
});

 $(document).ready(function() {
    var value = $("body").attr("data-key");
	if(value == 1){
		$("#oplist").val('1');
	} else if(value == 2){
		$("#oplist").val('2');
	}else if(value == 3){
		$("#oplist").val('3');
	}
    });

$(document)
		.ready(
				function() {
					var data_lang_param = $("body").attr("data-lang-param") == 'ar' ? 'ar'
							: 'en';
					$('#langlist').val(data_lang_param);
				});

$("#oplist").change(function () {                            
   var operator= $('#oplist').val();
   if(operator=='1'){
	window.location.href = "./stc";
   }else if(operator=='2'){
	window.location.href = "./mobily";
   }else if(operator=='3'){
	window.location.href = "./zain";
	}
    
});

// MSISDN subscriber
function subscribe() {

	comTxn();
	$(".error").html("").hide();
	var number = $("#msisdn").val();
	var keyValue = $("#oplist").val();
	var interfacee = $("body").attr("data-interfacee");
	if (number.length == 12 && number != null) {

		$.ajax({
			url : './send_pincode',
			type : 'GET',
			data :'msisdn='+number+'&key='+keyValue,
			dataType: 'json',
			contentType : 'application/json',
			mimeType : 'application/json',
			success : function(data, textStatus, jqXHR) {
				console.log(data);
				if (data['data']['sent'] == 1) {
					window.location.href = "./otp/form?key="
							+ keyValue + "&mobile=" + number+"&interfacee=" + interfacee;
				}

				else {
					$(".error").html("Something went wrong")
					$(".error").show();
				}

			},
			error : function(jqXHR, textStatus, errorThrown) {
					$(".error").html("Error Please Try Again")
					$(".error").show();
					$("#mobileOtp").val("");
			}

		});

	} else {

		var language = $("body").attr("data-lang-param") == 'ar' ? 'ar' : 'en';
		var en_msg = "Please enter a valid number ! (966xxxxxxxxx)";
		var ar_msg = "من فضلك أدخل رقما صالحا! (966xxxxxxxxx)";
		var errorMsg = language == 'ar' ? ar_msg : en_msg;
		$(".error").html(errorMsg);
		$(".error").show();
	}
	return false;
}

function comTxn(){
	var transid = $("body").attr("data-transid");
	var number = $("#msisdn").val();
	$.ajax({
		url : './comTxn',
		type : 'GET',
			data :'tid='+transid+'&msisdn='+number,
			dataType: 'json',
			contentType : 'application/json',
			mimeType : 'application/json',
		success: function (data, textStatus, jqXHR) {
	
		},
		error: function (jqXHR, textStatus, errorThrown) {
		
		}

	});
}