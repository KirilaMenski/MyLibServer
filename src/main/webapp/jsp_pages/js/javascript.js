/* Animation for drop menu*/
$(document).ready(function() {
	$('#menu_block ul li').hover(function() {
		$(this).find('#drop_menu').stop().fadeToggle(300);
	});
});

/* Show add div when clicked href add */
$(document).ready(function() {
	$('#add').click(function(event) {
		event.preventDefault();
		$('#add_display').slideToggle();
	});
});

function closeUpdate(id) {
	$('#update' + id).hide();
}
function showUpdate(id) {
	$('#update' + id).show();
}

// Added book to reading list
function addBookToList() {
	// TO DO
	var title = $('#input_book_title').val();
	var book_id = $('#input_book_id').val();
	var author = $('#input_author_name').val();
	var author_id = $('#input_author_id').val();
	$.ajax({
		url : 'add_book_to_list',
		data : ({
			title : title,
			book_id : book_id,
			author : author,
			author_id : author_id
		}),
		success : function(result) {
			$('#v_b_added').append(result);
		}
	});
}

// Change book status
function changeStatus(status, pick, id_in_list) {
	// TO DO
	var id = $('#r_l_input_id' + id_in_list).val();
	var title = $('#r_l_input_book_title' + id_in_list).val();
	var book_id = $('#r_l_input_book_id' + id_in_list).val();
	var author = $('#r_l_input_author_name' + id_in_list).val();
	var author_id = $('#r_l_input_author_id' + id_in_list).val();
	$.ajax({
		url : 'changeStatus',
		data : ({
			id : id,
			title : title,
			book_id : book_id,
			author : author,
			author_id : author_id,
			status : status,
			pick : pick
		}),
		success : function(result) {
			$('#r_l_status' + id_in_list).append(result);
		}
	});
}

// Liked citations
function doLiked(id) {
	$.ajax({
		url : 'doLiked',
		data : ({
			id : id
		}),
		success : function(result) {
			$('#c_status' + id).append(result);
		}
	});
}

// Like book
function likeBook(id, rating) {

	$.ajax({
		url : 'like_book',
		data : ({
			rating : rating,
			id : id
		}),
		success : function(result) {
			$('#v_b_rat').text(result);
		}
	});
}

$(document).ready(function() {

	$('#menu a').on('click', function() {
		$(this).siblings().removeClass('act').end().addClass('act');
	});
});

// Back to top button
$(document).ready(function() {

	// hide #back-top first
	$("#back-top").hide();

	// fade in #back-top
	$(function() {
		$(window).scroll(function() {
			if ($(this).scrollTop() > 400) {
				$('#back-top').fadeIn();
			} else {
				$('#back-top').fadeOut();
			}
		});

		// scroll body to 0px on click
		$('#back-top a').click(function() {
			$('body').animate({
				scrollTop : 0
			}, 800);
			return false;
		});
	});

});

// Animated cover books
$(document).ready(
		function() {
			$('.b_books_img, .m_last_added_img').width(100).height(140).hover(
					function() {

						$(this).stop().animate({
							width : 260,
							height : 380
						});
					}, function() {
						$(this).stop().animate({
							width : 100,
							height : 140
						});
					});

		});

// Slide show
function theRotator() {
	// Прозрачность всех картинок
	$('div#rotator ul li').css({
		opacity : 0.0
	});

	// Показ первой картинки
	$('div#rotator ul li:first').css({
		opacity : 1.0
	});

	// Вызов функции ротатор для запуска слайд шоу
	setInterval('rotate()', 5000);
}

function rotate() {

	// Первая картинка
	var current = ($('div#rotator ul li.show') ? $('div#rotator ul li.show')
			: $('div#rotator ul li:first'));

	// Берем следующую картинку, когда дойдем до последней начинаем с начала
	var next = ((current.next().length) ? ((current.next().hasClass('show')) ? $('div#rotator ul li:first')
			: current.next())
			: $('div#rotator ul li:first'));

	// картинки в случайном порядке
	var sibs = current.siblings();
	var rndNum = Math.floor(Math.random() * sibs.length);
	var next = $(sibs[rndNum]);

	// Подключаем эффект растворения/затухания для показа картинок, css-класс
	// show имеет больший z-index
	next.css({
		opacity : 0.0
	}).addClass('show').animate({
		opacity : 1.0
	}, 1000);

	// Прячем текущую картинку
	current.animate({
		opacity : 0.0
	}, 1000).removeClass('show');

}

$(document).ready(function() {
	theRotator();
});