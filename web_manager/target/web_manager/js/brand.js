new Vue({
   el:"#app",
   data:{
       brandList:[]
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
       }
    },
    created:function () {//创建vue对象之后调用
        this.findAllBrand();
    }
});