package com.fmjava.core.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.fmjava.core.pojo.entity.Result;
import com.fmjava.core.pojo.entity.pageResult;
import com.fmjava.core.pojo.entity.specEntity;
import com.fmjava.core.pojo.specification.Specification;
import com.fmjava.core.service.specificationService;
import org.apache.ibatis.executor.ResultExtractor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/spec")
public class specController {

    @Reference
    private specificationService specificationService;

    @RequestMapping("/findPage")
    public pageResult fingPage(Integer page, Integer pageSize, @RequestBody Specification specification){
        return specificationService.findPage(page, pageSize, specification);
    }

    @RequestMapping("/findSpecWithId")
    public specEntity findSpecWithId(Long id){
        return specificationService.findSpecEntity(id);
    }
    @RequestMapping("/saveSpecAndOption")
    public Result saveSpecAndOption(@RequestBody specEntity specEntity){//只要是json数据 必须解析
        try{
            specificationService.save(specEntity);
            return new Result("保存成功",true);
        }catch (Exception e){
            return new Result("保存失败",false);
        }
    }
    @RequestMapping("/updateSpecAndOption")
    public Result updateSpecAndOption(@RequestBody specEntity specEntity){
        try{
            specificationService.update(specEntity);
            return new Result("更新成功",true);
        }catch (Exception e){
            return new Result("更新失败",false);
        }
    }

    @RequestMapping("/deleteSpec")
    public Result deleteSpec(Long [] ids){
        try{
            if(ids!=null){
                specificationService.deleteSpec(ids);
                return new Result("删除成功",true);
            }
            return new Result("请选择一行进行删除",true);
        }catch (Exception e){
            return new Result("删除失败",false);
        }
    }

    @RequestMapping("/selectOptionList")
    public List<Map> selectOptionList(){
        return specificationService.selectOneOptionList();
    }
}
