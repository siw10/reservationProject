/**
 * 
 */
let index = {
    init: function () {
        // jQuery 사용
        
        
       $("#btn-update").on("click", () => {
            this.update();
        });
        
       $("#btn-modify").on("click", () => {
			const con_check = confirm("수정하시겠습니까?");
			if(con_check == true){
            	this.modify();
            }
        });
        
        $("#btn-delete").on("click", () => {
			const con_check = confirm("삭제하시겠습니까?");
			if(con_check == true){
				this.delete();
				
			}
		});
		
    },



     update: function () {
        let data = {
			pno:$("#pno").val(),
            conPrice: $("#conPrice").val(),
            dogNumPrice: $("#dogNumPrice").val(),
            dogSizePrice: $("#dogSizePrice").val(),
            familyPrice: $("#familyPrice").val(),
            idPrice: $("#idPrice").val(),
            peopleNumPrice: $("#peopleNumPrice").val(),
            pictureSizePrice: $("#pictureSizePrice").val(),
            spaPrice: $("#spaPrice").val(),
            swimPrice: $("#swimPrice").val()
			            
        }

        $.ajax({
            type: "PUT",
            url: "/priceModify",
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            dataType: "json"
        }).done(function (res) {
            alert(res.data);
            location.href = "javascript:location.reload()";
        }).fail(function (error) {
            alert("통신에 에러가 발생했습니다. 잠시 후 다시 시도해주세요.");
        });

    },
    
     modify: function () {
        let data = {
			bno:$("#bno").text(),
			btype:$("#btype").val(),
			themeName:$("#themeName").val(),
			username:$("#username").val(),
			name:$("#name").val(),
			phone:$("#phone").val(),
			dogSize:$("#dogSize").val(),
			pictureSize:$("#pictureSize").val(),
			dogNum:$("#dogNum").val(),
			people:$("#people").val(),
			price:$("#price").val(),
			bookingDate:$("#bookingDate").val()
			            
        }

        $.ajax({
            type: "PUT",
            url: "/bookingUpdate",
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            dataType: "json"
        }).done(function (res) {
            alert(res.data);
            location.href = "javascript:location.reload()";
        }).fail(function (error) {
            alert("통신에 에러가 발생했습니다. 잠시 후 다시 시도해주세요.");
        });

    },
    
    delete:function () {
        let data = {
			bno:$("#bno").text(),
			bookingDate:$("#bookingDate").val()
			            
        }

        $.ajax({
            type: "PUT",
            url: "/bookingRemove",
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            dataType: "json"
        }).done(function (res) {
            alert(res.data);
            location.href = "/admin/adminPage3";
        }).fail(function (error) {
            alert("통신에 에러가 발생했습니다. 잠시 후 다시 시도해주세요.");
        });

    }
    

}


index.init();