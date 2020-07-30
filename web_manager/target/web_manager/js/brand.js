new Vue({
   el:"#app",
   data:{
       brandList:[],
       brand:{
           name:'',
           firstChar:''
       },
       page:1,//当前页码
       pageSize:5,//每页显示条数
       total:0,//总条数
       maxPage:9,//最大页数
       selectedId:[],//选中id集合
       searchBrand:{//搜索品牌实体
           name:'',
           firstChar:''
       }
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
        pageHandler:function (page) {  //分页方法 参数是当前页码
           this.page=page;
           //点击每页的时候刷新点击页check的状态   不会！
           // alert(page)
           var _this=this;
            axios.post("/brand/findPage.do?page="+this.page+"&pageSize="+this.pageSize,this.searchBrand).then(function (response) {
                console.log(response.data);
                //给vue的参数赋值
                _this.total=response.data.total;
                _this.brandList=response.data.pageList;
            }).catch(function (reason) {
                console.log(reason);
            });
        },
        saveBrand:function () {
           var _this=this;
           if(_this.brand.id!=null){//通过有无id判断修改的添加
               axios.post("/brand/updateBrand.do",_this.brand).then(function (response) {
                   if(response.data.success){
                       alert(response.data.message);
                       //刷新界面
                       _this.pageHandler(_this.page);
                       _this.brand={};
                   }else {
                       console.log(response.data.message);
                   }

               }).catch(function (reason) {
                   console.log(reason);
               });
           }else {
               axios.post("/brand/add.do",_this.brand).then(function (response) {
                   if(response.data.success){
                       alert(response.data.message);
                       //刷新界面
                       _this.pageHandler(1);
                   }else {
                       console.log(response.data.message);
                   }

               }).catch(function (reason) {
                   console.log(reason);
               });
           }
        },
        findById:function (id) {//通过id回显数据
            var _this=this;
            axios.get("/brand/findById.do",{params:{id:id}}).then(function (response) {
                _this.brand=response.data;
            }).catch(function (reason) {
                console.log(reason);
            });
        },
        deleteSelected:function (event,id) {//通过点击check
            // console.log(event.target.checked);//check状态
            // console.log(id);//点击的id
            if(event.target.checked){
                //选中
                this.selectedId.push(id);
            }else {
                //取消选中
                //移除元素
                var idx=this.selectedId.indexOf(id);
                this.selectedId.splice(idx,1);
            }
            console.log(this.selectedId)
        },
        deleteBrands:function () {
            var _this=this;
            //qs插件 处理数组   必须是post请求！！！！
            axios.post('/brand/deleteBrands.do',Qs.stringify({ids:_this.selectedId},{indices:false}))
                .then(function (response) {
                    if(response.data.success){
                        alert(response.data.message);
                        //刷新当前页面 和 删除数组
                        _this.pageHandler(_this.page);
                        _this.selectedId=[];
                    }else {
                        alert(response.data.message)
                    }

            }).catch(function (reason) {
                alert(reason.message)
            });
       }
    },
    created:function () {//创建vue对象之后调用
        // this.findAllBrand();
        this.pageHandler(1);//初始化第一页
    }
});