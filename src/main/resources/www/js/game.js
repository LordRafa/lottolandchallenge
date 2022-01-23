$(document).ready(function() {

	$.ajax({
		url: 'rest/rps2p/rounds',
		dataType: 'json',
		success: function(roundsRespose) {

			let roundsNumber = $('#roundsNumber');
			roundsNumber.text(roundsRespose.roundsNumber);

			var appBody = $('#rounds');

			for (let round of roundsRespose.rounds) {

				let winner = "";
				if (round.winner === 0) {
					winner = 'Draw';
				} else {
					winner = 'Player ' + round.winner + ' Wins';
				}

				appBody.append(
					'<tr>' +
					'<td>' + round.playersChoices[0] + '</td>' +
					'<td>' + round.playersChoices[1] + '</td>' +
					'<td>' + winner + '</td>' +
					'</tr >'
				);

			}

		},
		error: function() {
			console.log('Error');
		}
	});

	$('#playBtn').click(function() {

		let playersMsg = {
			"players": ["TheRock", "Rnd"]
		};

		$.ajax({
			type: "POST",
			url: 'rest/rps2p/rounds',
			dataType: 'json',
			contentType: "application/json",
			data: JSON.stringify(playersMsg),
			success: function(round) {

				let roundsNumber = $('#roundsNumber');
				counter = parseInt(roundsNumber.text()) + 1;
				roundsNumber.text("" + counter);

				var appBody = $('#rounds');

				let winner = "";
				if (round.winner === 0) {
					winner = 'Draw';
				} else {
					winner = 'Player ' + round.winner + ' Wins';
				}

				appBody.append(
					'<tr>' +
					'<td>' + round.playersChoices[0] + '</td>' +
					'<td>' + round.playersChoices[1] + '</td>' +
					'<td>' + winner + '</td>' +
					'</tr >'
				);

			},
			error: function() {
				console.log('Error');
			}
		});
	});

	$('#resetBtn').click(function() {
		$.ajax({
			url: 'rest/rps2p/rounds',
			type: 'DELETE',
			success: function() {

				let roundsNumber = $('#roundsNumber');
				roundsNumber.text("" + 0);

				var rounds = $('#rounds');
				rounds.empty();

			},
			error: function() {
				console.log('Error');
			}
		});
	});

});
