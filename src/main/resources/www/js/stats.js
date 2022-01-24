$(document).ready(function() {

	$.ajax({
		url: 'rest/rps2p/stats',
		dataType: 'json',
		success: function(gameStats) {

			var appBody = $('#appBody');

			for (let key in gameStats.playerWins) {
				appBody.append(
					'<div class="row">' +
					'<div class="col">Total ' + key + ' Wins: ' + gameStats.playerWins[key] + '</div>' +
					'</div>'
				);
			}

			appBody.append(
				'<div class="row">' +
				'<div class="col">Total Draws: ' + gameStats.totalDraws + '</div>' +
				'</div>'
			);

			appBody.append(
				'<div class="row">' +
				'<div class="col">Total Games: ' + gameStats.totalGames + '</div>' +
				'</div>'
			);

		},
		error: function() {
			console.log('Error');
		}
	});


});
