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
       },
        specEntity:{
            spec:{},
            specOption:[]
        },
        selectedId:[]
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
       },
        addTableRow:function () {
            this.specEntity.specOption.push({})
        },
        deleteTableRow:function (index) {
            this.specEntity.specOption.splice(index,1);
        },
        saveSpecAndOption:function () {
            var url='';
            if(this.specEntity.spec.id!=null){
                url="/spec/updateSpecAndOption.do";
            }else {
                url='/spec/saveSpecAndOption.do';
            }
            var _this=this;
            axios.post(url,_this.specEntity)
                .then(function (response) {
                    if(response.data.success){
                        alert(response.data.message);
                        //新增之后 刷新界面 选项名字 和 选项卡对象集合
                        _this.specEntity.spec={};
                        _this.specEntity.specOption=[];
                        _this.pageHandler(1);
                    }else{
                        alert(response.data.message);
                    }

                }).catch(function (reason) {
                console.log(reason);
            });
        },
        findSpecWithId:function (id) {
           var _this=this;
            axios.get("/spec/findSpecWithId.do",{params: {id:id}}).then(function (response) {
                _this.specEntity=response.data;
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
        deleteSpec:function () {
            var _this=this;
            //qs插件 处理数组   必须是post请求！！！！
            axios.post('/spec/deleteSpec.do',Qs.stringify({ids:_this.selectedId},{indices:false}))
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
    created:function () {
        this.pageHandler(1);
    }


});