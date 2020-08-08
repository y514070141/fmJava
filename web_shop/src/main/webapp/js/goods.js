new Vue({
   el:"#app",
   data:{
       categoryList1:[],//分类1数据列表
       categoryList2:[],//分类2数据列表
       categoryList3:[],//分类3数据列表
       grade:1,  //记录当前级别
   },
    methods:{
       loadData:function (id) {
           var _this=this;
           axios.post("/item_cat/findByParentId.do?id="+id)
               .then(function (response) {
                   if(_this.grade==1){
                       _this.categoryList1=response.data;
                   }
                   if(_this.grade==2){
                       _this.categoryList2=response.data;
                   }
                   if(_this.grade==3){
                       _this.categoryList3=response.data;
                   }
               }).catch(function (reason) {
               alert(reason)
           })
       }
    },
    created:function () {
        this.loadData(0);
    }
});