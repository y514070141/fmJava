new Vue({
   el:"#root",
    data:{
       specList:[],
        page:1,
        pageSize:5,
        total:0,
        maxPage:9,//最大页数
        searchSpec:{
           specName:''
       }
    },
    methods:{
       pageHandler:function (page) {
           this.page=page;
           var _this=this;
           axios.post("/spec/findPage.do?page="+this.page+"&pageSize="+this.pageSize,this.searchSpec)
               .then(function (response) {
                   _this.specList=response.data.pageList;
                   _this.total=response.data.total;
               }).catch(function (reason) {
                   console.log(reason);
           });
       }
    },
    created:function () {
        this.pageHandler(1);
    }


});