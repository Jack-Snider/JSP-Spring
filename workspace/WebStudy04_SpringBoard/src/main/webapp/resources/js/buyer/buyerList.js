/**
 * 
 */
	$.ajax({
		url : "../prod/getLprodList.do",
		dataType : "json",
		success : function(resp) {
			let buyerLguTag = searchUI.find("[name=buyerLgu]");
			let selectedValue = buyerLguTag.data("buyerLgu");
			let lprodList = resp.model;
			let options = [];
			$.each(lprodList, function(index, lprod){
				let option = $("<option>").attr("value", lprod.lprodGu)
											.text(lprod.lprodNm);
				if(selectedValue == lprod.lprodGu){
					option.prop("selected", true);
				}
				
				options.push(option);
			});
			buyerLguTag.append(options);
		},
		error : function(errorResp) {
			console.log(errorResp.status);
		}
	});
	
	let searchUI = $("#searchUI").on("click", "#searchBtn", function(event){
		let inputTags = searchUI.find(":input[name]");
		$.each(inputTags, function(index, inputTag){
			let name = $(this).attr("name");
			let value = $(this).val();
			searchForm.get(0)[name].value = value;
		});
		searchForm.submit();
	});
	let searchForm = $("#searchForm");
	let pageTag = $("[name=page]");
	$(".pagingArea").on("click", "a", function(event){
		event.preventDefault();
		let page = $(this).data("page");
		if(!page) return false;
		pageTag.val(page);
		searchForm.submit();
		return false;
	});