/**
 * 
 */
window.addEventListener("DOMContentLoaded", function(event){
	$.fn.sessionTimer=function(timeout, msgModal){
		if(!timeout || typeof timeout != 'number' || timeout <= 0)
			throw Error("타이머 처리 불가");
		const SPEED = 100;
		let timer = timeout;
		let timerJob = null;
		let msgJob = null;
		let timerArea = this;
		let msgArea = $(msgModal).on("click", ".ctrlBtn", function(){
			let command = $(this).text();
			if('YES' == command){
				init();
				$.ajax({
					method : "head"
				});
			}
			msgArea.modal('hide');
		});
		
		let init = function(){
			timer = timeout;
			if(timerJob==null)
				timerJob = setInterval(function(){
					timerArea.html(moment(--timer*1000).format('mm:ss'));
					if(timer <= 0)
						clearInterval(timerJob);
				}, 1 * SPEED);
			if(msgJob!=null)
				clearTimeout(msgJob);
			msgJob = setTimeout(function(){
				msgArea.modal('show');
			}, (timeout - 60) * SPEED);
		}
		
		init();
	}
	
});
