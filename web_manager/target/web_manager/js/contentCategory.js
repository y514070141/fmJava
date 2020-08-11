new Vue({
    el:"#app",
    data:{
        page: 1,  //显示的是哪一页
        pageSize: 5, //每一页显示的数据条数
        total: 0, //记录总数
        maxPage:0,
        searchEntity:{},
        categoryList:[],
        //selectIds:[] //记录选择了哪些记录的id
    },
    methods:{
        pageHandler: function (page) {
            var _this = this;
            this.page = page;//impl接收的参数 是 page pageSize不是外面的this.page
            axios.post("/contentCategory/search.do?page="+this.page+"&pageSize="+this.pageSize,this.searchEntity)
                .then(function (response) {
                    //取服务端响应的结果
                    _this.categoryList = response.data.pageList;
                    _this.total = response.data.total;
                }).catch(function (reason) {
                console.log(reason);
            })
        }
    },
    created: function() {
        this.pageHandler(1);
    },
});