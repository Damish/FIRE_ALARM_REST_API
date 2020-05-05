let sensorData = [];

function getDetails() {
	setInterval(function() {

		jQuery.ajax({
			url : "http://localhost:8080/SensorData_REST_API/rest/sensors/"
					+ $("#id").val(),
			type : "GET",
			contentType : "application/json",
			dataType : 'json',
			success : function(data, textStatus, errorThrown) {

				$("#sid").text(data.sid);
				$("#co2").text(data.co2Level);
				$("#smoke").text(data.smokeLevel);
				$("#floor").text(data.floorNo);
				$("#room").text(data.roomNo);

			},
			error : function(jqXHR, textStatus, errorThrown) {
				$("#status1").text("Sorry! sensor not found!");

			},
			
		});

	}, 1000);
};

function getAllDetails() {

	jQuery.ajax({
		url : "http://localhost:8080/SensorData_REST_API/rest/sensors",
		type : "GET",
		contentType : "application/json",
		dataType : 'json',
		success : function(data, textStatus, errorThrown) {

			sensorData = data;
			$("#status2").text("All data fetched sucessfully!!!");

		},
		error : function(jqXHR, textStatus, errorThrown) {
		},
		timeout : 1000,
	});

};

function updateDetails() {

	getAllDetails(); //fetch all data to sensorData Array
	 
	let count = -1;
	
	setInterval(function() {

		for (let x = 0; x <= sensorData.length; x++) {

			count += 1;

			if (count == sensorData.length) {
				count = 0;
			}

			var co2_random = Math.floor((Math.random() * 10) + 1);
			var smoke_random = Math.floor((Math.random() * 10) + 1);

			jQuery.ajax({
				url : "http://localhost:8080/SensorData_REST_API/rest/sensors/"
						+ sensorData[count].sid + "/" + co2_random + "/"
						+ smoke_random + "/",
				type : "PUT",
				contentType : "application/json",
				dataType : 'json',
				success : function(data, textStatus, errorThrown) {

					$("#status3").text("Sensor Auto update started!");

				},
				error : function(jqXHR, textStatus, errorThrown) {

					$("#status3").text("Sensor not Updated !!!");
				},
			// timeout : 1000,
			});
		}

	}, 10000);

	
}





