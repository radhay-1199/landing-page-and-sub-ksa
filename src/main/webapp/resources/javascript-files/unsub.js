	$('#langlist').on('change', function() {
		lang=$('#langlist').val() == 'ar' ? 'ar' : 'en';
		var url_string = window.location.href;
		var url = new URL(url_string);
		window.location.assign("./unsub?lang="+lang);			
		});	
	
	$(document).ready(function () {
		var data_lang_param =$("body").attr("data-lang-param") == 'ar' ? 'ar' : 'en';
		 $('#langlist').val(data_lang_param);
	});

$(document).ready(function() {
    var value = $('#operator').val();
	if(value == "1"){
		$("#oplist").val('1');
	} else if(value == "2"){
		$("#oplist").val('2');
	}else if(value == "3"){
		$("#oplist").val('3');
	}
    });

function unsubscribe(){
		$(".error").html("").hide();
	var number = $("#msisdn").val();
	var keyValue = $("#opList").val();
	if (number.length == 12 && number != null) {

		$.ajax({
			url : './unsubscribe',
			type : 'GET',
			data : {
				msisdn : number,
				key : keyValue
			},
			dataType: 'json',
			success : function(data, textStatus, jqXHR) {
				if (data['data']['msg'] == "success") {
					$("#frm-mobile-verification").hide();
					$(".error").html("Your request is being processed");
					$(".error").css('text-align','center');
					$(".error").show();
				}

				else {
					$(".error").html(data['data']['message'])
					$(".error").show();
				}

			},
			error : function(jqXHR, textStatus, errorThrown) {

			}

		});

	} else if (number.length == 10 && number != null){
		var newNumber = "966"+number.subtring(1);
		
		$.ajax({
			url : './unsubscribe',
			type : 'GET',
			data : {
				msisdn : newNumber,
				key : keyValue
			},
			dataType: 'json',
			success : function(data, textStatus, jqXHR) {
				if (data['data']['msg'] == "success") {
					$("#frm-mobile-verification").hide();
					$(".error").html("Your request is being processed");
					$(".error").css('text-align','center');
					$(".error").show();
				}

				else {
					$(".error").html(data['data']['message'])
					$(".error").show();
				}

			},
			error : function(jqXHR, textStatus, errorThrown) {
					$(".error").html("Error Please Try Again")
					$(".error").show();
					$("#mobileOtp").val("");
			}

		});
	}else {

		var language = $("body").attr("data-lang-param") == 'ar' ? 'ar' : 'en';
		var en_msg = "Please enter a valid number ! (966xxxxxxxxx)";
		var ar_msg = "من فضلك أدخل رقما صالحا! (966xxxxxxxxx)";
		var errorMsg = language == 'ar' ? ar_msg : en_msg;
		$(".error").html(errorMsg);
		$(".error").show();
	}
	return false;
}