new Vue({
   el:"#app",
   data:{
       brandList:[],
       page:1,//当前页码
       pageSize:10,//每页显示条数
       total:100,//总条数
       maxPage:10,//最大页数
   },
    methods:{
       findAllBrand:function () {//请求所有品牌
           var _this=this;//vue的this和axios的this不是一个
           axios.get("/brand/findAllBrand.do").then(function (response) {
               console.log(response.data);
               //给vue的参数赋值
               _this.brandList=response.data;
           }).catch(function (reason) {
               console.log(reason);
           });
       },
        pageHandler:function (page) {//分页方法 参数是当前页码
            this.page=page;
        }
    },
    created:function () {//创建vue对象之后调用
        this.findAllBrand();
    }
});