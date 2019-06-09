$(document).ready(function() {
	
	function getUrlParameter(sParam) {
	    var sPageURL = window.location.search.substring(1),
	        sURLVariables = sPageURL.split('&'),
	        sParameterName,
	        i;

	    for (i = 0; i < sURLVariables.length; i++) {
	        sParameterName = sURLVariables[i].split('=');

	        if (sParameterName[0] === sParam) {
	            return sParameterName[1] === undefined ? true : decodeURIComponent(sParameterName[1]);
	        }
	    }
	};
	
	//Set Hidden div base url parameter value
	var urlParmVal = getUrlParameter('loc');
	if(urlParmVal === undefined){
		$('#landingpage-information').show();
		$('#weatherpage-information').hide();
	}
	else{
		$('#landingpage-information').hide();
		$('#weatherpage-information').show();
		
		if(urlParmVal =="all"){
			$('#menu_allweather').addClass('active');
			$('#menu_allweather_collapse').addClass('active');
		}
		else if(urlParmVal =="campbell"){
			$('#menu_campbellWeather').addClass('active');
			$('#menu_campbellWeather_collapse').addClass('active');
		}
		else if(urlParmVal =="omaha"){
			$('#menu_omahaWeather').addClass('active');
			$('#menu_omahaWeather_collapse').addClass('active');
		}
		else if(urlParmVal =="austin"){
			$('#menu_austinWeather').addClass('active');
			$('#menu_austinWeather_collapse').addClass('active');
		}
		else if(urlParmVal =="niseko"){
			$('#menu_nisekoWeather').addClass('active');
			$('#menu_nisekoWeather_collapse').addClass('active');
		}
		else if(urlParmVal =="nara"){
			$('#menu_naraWeather').addClass('active');
			$('#menu_naraWeather_collapse').addClass('active');
		}
		else if(urlParmVal =="jakarta"){
			$('#menu_jakartaWeather').addClass('active');
			$('#menu_jakartaWeather_collapse').addClass('active');
		}
		else {
			$('#landingpage-information').show();
			$('#weatherpage-information').hide();
		}
	}
	
});



