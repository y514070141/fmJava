new Vue({
    el: "#app",
    data:{
        categoryList:[],
        grade:1,
        grandEntity1:{},//第一级实体
        grandEntity2:{}//第二季实体
    },
    methods: {
        selectCateByParentId: function (id) {
            var _this = this;
            axios.post("/itemCat/findByParentId.do?parentId="+id)
                .then(function (response) {
                    //取服务端响应的结果
                        _this.categoryList =response.data;
                    console.log(response);
                }).catch(function (reason) {
                console.log(reason);
            })
        },
        nextGrade:function (item) {
            // this.grade=this.grade+1;
            if (this.grade == 1){//如果当前是第一级, 面包屑导航为空
                this.grandEntity1 = {};
                this.grandEntity2 = {};
            }
            if (this.grade == 2){ //第2级,把当前的分类显示在第1个面包屑上
                this.grandEntity1 = item;
                this.grandEntity2 = {};
            }
            if (this.grade == 3){ //第3级,把当前点击分类显示到第二个面包屑上
                this.grandEntity2 = item;
            }
            this.selectCateByParentId(item.id);
        },
        setGrade:function(value){
            this.grade = value;
        }
    },
    created: function () {
        this.selectCateByParentId(0);
    }
});
