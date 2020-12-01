$body = $("body");

$(document).on({
    ajaxStart: function() { $body.addClass("loading");    },
     ajaxStop: function() { $body.removeClass("loading"); }    
});

	$('#langlist').on('change', function() {
		lang=$('#langlist').val() == 'ar' ? 'ar' : 'en';
		var url_string = window.location.href;
		var url = new URL(url_string);
		window.location.assign("../otp/form?lang="+lang);			
		});	
	
	$(document).ready(function () {
		var data_lang_param =$("body").attr("data-lang-param") == 'ar' ? 'ar' : 'en';
		 $('#langlist').val(data_lang_param);
	});
//Verify OTP 
var mobile = $("body").attr("data-mobile");
var keyValue = $("body").attr("data-key-value");
//var RequestId=$("body").attr("data-RequestId");
function verifyOTP() {
	$(".error").html("").hide();
	$(".success").html("").hide();
	var otp = $("#mobileOtp").val();
	if (otp.length != 0) {
		
		$.ajax({
			url : '../verify_pin',
			type: 'GET', 
			dataType: 'json', 
			data: {
				msisdn : mobile,
				key : keyValue,
				pincode : otp
			},
			contentType : 'application/json',
			mimeType : 'application/json',
			success: function (data, textStatus, jqXHR) {
				if(data['data']['subscribe'] == true){
					comTxn();
					$("#frm-mobile-verification").hide();
					$(".error").html("Thank You for Subscribing.<br>Your request is being processed");
					$(".error").css('text-align','center');
					$(".error").show();
				}

				else{
					$(".error").html(data['data']['message'])
					$(".error").show();
					$("#mobileOtp").val("");
				}

			},
			error: function (jqXHR, textStatus, errorThrown) {
					$(".error").html("Error Please Try Again")
					$(".error").show();
					$("#mobileOtp").val("");
			}

		});
	
	}
	else{
		$(".error").html('لقد أدخلت OTP خاطئ.')
		$(".error").show();
	}
}


function comTxn(){

var serviceId;

if(keyValue == 1){
	serviceId=123;
}else if(keyValue == 2){
	serviceId=2297;
}else if(keyValue == 3){
	serviceId=2300;
}

	var comTxn  ={
			"msisdn":$('#msisdn').val(),
			"productID":"1_SAR",
			"price":"1.00",
			"interfaceMedium":"supercombo_1_daily",   
			"billerID":"sa",
			"publisher":"supercombo",
			"serviceID":serviceId
	}
	
	$.ajax({
		url : '../comTxn',
		method: 'POST', 
		dataType: 'json', 
		data: JSON.stringify(comTxn),
		contentType: 'application/json',
		mimeType: 'application/json',
		success: function (data, textStatus, jqXHR) {
	
		},
		error: function (jqXHR, textStatus, errorThrown) {
		
		}

	});
}