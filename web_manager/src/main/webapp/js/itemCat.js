new Vue({
    el: "#app",
    data:{
        categoryList:[],
    },
    methods: {
        selectCateByParentId: function (id) {
            _this = this;
            axios.post("/itemCat/findByParentId.do?parentId="+id)
                .then(function (response) {
                    //取服务端响应的结果
                    _this.categoryList =response.data;
                    console.log(response);
                }).catch(function (reason) {
                console.log(reason);
            })
        },
    },
    created: function () {
        this.selectCateByParentId(0);
    }
});
