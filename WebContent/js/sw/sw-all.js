$(document).ready(
		function() {
			$('.js_tab_work a').swnavi(
					{
						target : 'my_works',
						after : function(event) {
							$(event.target).addClass('current').siblings()
									.removeClass('current');
						}
					});

			$('.js_tab_com a').swnavi(
					{
						target : 'my_communities',
						after : function(event) {
							$(event.target).addClass('current').siblings()
									.removeClass('current');
						}
					});

			$('a.js_content').swnavi({
				target : 'content'
			});

			$('.js_notice_count a').swnavi(
					{
						target : 'notice_message_box',
						before : function(event) {
							$('#notice_message_box').hide();
							$('#notice_message_box').slideDown();
						},
						after : function(event) {
							$('.js_notice_count').find('a').removeClass(
									'current');
							$(event.target).parents('.js_notice_count:first')
									.find('a').addClass('current');
						}
					});

			$('.js_close_message').live('click', function(e) {
				$('#notice_message_box').slideUp();
				setTimeout(function() {
					$('.js_notice_count').find('a').removeClass('current');
				}, 500);
				return false;
			});

			$('.js_select_action')
					.live(
							'click',
							function(e) {
								$('.js_select_action').siblings(
										'.js_upload_form').hide().find(
										'.js_upload_form_detail').hide();
								$('.js_select_action').find('a').removeClass(
										'current');
								var targetId = $(e.target).parents('li:first')
										.find('a').addClass('current').attr(
												'id');
								$('#' + targetId + '_box').show();
								return false;
							});

			$('.js_upload_input').live(
					'click',
					function(e) {
						$(e.target).parents('div.js_upload_input').siblings(
								'div.js_upload_form_detail').slideDown(500);
					});

			$('.js_collapse_slide').live(
					'click',
					function(e) {
						$(e.target).parent().siblings('.js_collapsible')
								.slideToggle(500);
					});

			$('input.js_auto_complete').live('keyup', function(e) {
				var input = $(e.target);
				var target = input.parent().siblings('div');
				var url = input.attr('href');
				var lastValue = input[0].value;
				setTimeout(function() {
					var currentValue = input[0].value;

					if (lastValue === currentValue) {
						$.ajax({
							url : url,
							data : {
								key : input[0].value
							},
							context : input,
							success : function(data, status, jqXHR) {
								target.show();
								target.html(data);
							}
						});
					} else {
					}
				}, 500);
			});

			$('input.js_auto_complete').live('focusout', function(e) {
				var input = $(e.target);
				var target = input.parent().siblings('div');
				setTimeout(function() {
					input[0].value = '';
					target.hide();
				}, 500);
			});

			var lastCatTarget = null;
			var lastGroupTarget = null;
			$('.js_drill_down').live(
					'click',
					function(e) {
						var input = $(e.target);
						var target = input.siblings('div');
						var url = input.attr('targetContent');
						var categoryId = input[0].getAttribute("categoryId");
						var groupId = input[0].getAttribute("groupId");
						if (lastCatTarget != null && categoryId != null) {
							lastCatTarget.slideToggle();
							lastCatTarget = null;
						} else if (lastGroupTarget != null && groupId != null) {
							lastGroupTarget.slideToggle();
							lastGroupTarget = null;
						}
						if (url == 'undefined'
								|| (categoryId == null && groupId == null)) {
							return;
						}
						$.ajax({
							url : url,
							data : {
								categoryId : categoryId,
								groupId : groupId
							},
							context : input,
							success : function(data, status, jqXHR) {
								target.slideToggle();
								target.html(data);
								if (categoryId != null)
									lastCatTarget = target;
								else if (groupId != null)
									lastGroupTarget = target;
							}
						});
					});
			$('.js_more_list').live('click', function(e) {
				var anchor = $(e.target);
				var base_ul = anchor.parent().prev('ul');
				$.ajax({
					url : anchor.attr('href'),
					data : {
//						last_id : null
					},
					success : function(data, status, jqXHR) {
						$(data).appendTo(base_ul);
					}
				});

				return false;
			});

			$(window).scroll(
					function() {
						var more_anchor = $('#work_ing .js_more_list a');
						if ($(window).scrollTop() == $(document).height()
								- $(window).height() && (more_anchor.length > 0 && !more_anchor.isWaiting)) {
							more_anchor.isWaiting = true;
							setTimeout(function() {
								if ($(window).scrollTop() == $(document).height()
										- $(window).height())
									more_anchor.trigger('click');
								more_anchor.isWaiting = false;
							}, 2000);
						}
					});
		});