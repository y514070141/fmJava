Vue.component('v-select', VueSelect.VueSelect);

new Vue({
    el:"#app",
    data:{
        tempList:[],

        TypeTemplate:{
            name:''
        },

        searchTemp:{
            name:''
        },
        page: 1,  //显示的是哪一页
        pageSize: 5, //每一页显示的数据条数
        total: 0, //记录总数
        maxPage:9,

        brandsOptions: [//id，name 对象集合
            // {"id": 1, "text": "联想"},
            // {"id": 2, "text": "三星"},
            // {"id": 3, "text": "华为"},
        ],
        placeholder: '可以进行多选',
        selectBrands: [],//事件数据 可以 选择的对象数据
        sel_brand_obj: [],//事件同步的对象数组


        specOptions:[],
        // selectedSpec:[],
        sel_spec_obj:[],

    },
    methods:{
        pageHandler:function (page) {
            var _this=this;
            _this.page=page;
            axios.post("/templates/findPage.do?page="+this.page+"&pageSize="+this.pageSize,this.searchTemp)
                .then(function (response) {
                    console.log(response.data);
                        _this.total=response.data.total;
                        _this.tempList=response.data.pageList;
                }).catch(function (reason) {
                console.log(reason);
            });
        },
        jsonToString:function (jsonStr,key) {
            jsonObj=JSON.parse(jsonStr);
            var value="";
            for(var i=0;i<jsonObj.length;i++){
                if(i>0){
                    value+=',';
                }
                value+=jsonObj[i][key];
            }
            return value;
        },
        selected_brand: function(values){//values就是传过来的数据
            // console.log(values);
            this.selectBrands =values.map(function(obj){
                return obj;
            });
            // console.log(this.selectBrands);
            // console.log(this.sel_brand_obj)
        },
        selLoadData:function () {
            _this = this;
            axios.get("/brand/selectOptionList.do")
                .then(function (response) {
                    _this.brandsOptions = response.data;
                }).catch(function (reason) {
                console.log(reason);
            })
        },
        selected_spec:function (values) {
            // console.log(values);
            values.map(function (obj) {

            });
            // console.log(this.sel_spec_obj)
        },
        setLoadData2:function(){
            axios.get("/spec/selectOptionList.do")
                .then(function (response) {
                    _this.specOptions = response.data;
                }).catch(function (reason) {
                console.log(reason);
            });
        },
        save:function () {
            var entity = {
                name:this.TypeTemplate.name,//对应模板类的name
                specIds:this.sel_spec_obj,//对应模板specIds规格
                brandIds:this.sel_brand_obj,//品牌
                // customAttributeItems:this.otherExtends
            };
            var _this=this;
            axios.post("/templates/add.do",entity)
                .then(function (response) {
                    if(response.data.success){
                        alert(response.data.message);
                        _this.pageHandler(1);
                    }else {
                        alert(response.data.message)
                    }
                }).catch(function (reason) {
                console.log(reason);
            });
        },
    },
    created:function () {
        this.pageHandler(1);
        this.selLoadData();
        this.setLoadData2();
    }

});