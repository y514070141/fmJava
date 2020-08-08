new Vue({
   el:"#app",
   data:{
       categoryList1:[],//分类1数据列表
       categoryList2:[],//分类2数据列表
       categoryList3:[],//分类3数据列表
       grade:1,  //记录当前级别
       cateSelect1:-1,
       cateSelect2:-1,
       cateSelect3:-1,

       typeId:0//模板id
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
       },
        change:function (grade) {
            if(grade==1){
                this.cateSelect2=-1;//请空
                this.categoryList2=[];
                this.cateSelect3=-1;
                this.categoryList3=[];
                
                this.grade=grade+1;
                this.loadData(this.cateSelect1);
            }
            if(grade==2){
                this.cateSelect3=-1;
                this.categoryList3=[];

                this.grade=grade+1;
                this.loadData(this.cateSelect1);
            }
            if(grade==3){
                //处理模板
                var _this=this;
                axios.post("/item_cat/findOne.do?id="+this.cateSelect3)
                    .then(function (response) {
                        _this.typeId=response.data.typeId;
                    }).catch(function (reason) {
                    alert(reason)
                });
            }
        }
    },
    created:function () {
        this.loadData(0);
    }
});