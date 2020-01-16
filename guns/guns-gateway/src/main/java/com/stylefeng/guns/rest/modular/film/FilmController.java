package com.stylefeng.guns.rest.modular.film;

import com.alibaba.dubbo.config.annotation.Reference;

import com.stylefeng.guns.rest.bean.film.*;
import com.stylefeng.guns.rest.film.service.FilmService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FilmController {
    @Reference
    FilmService filmService;

    @RequestMapping("film/getIndex")
    public FilmIndexVo getIndex(){
        FilmIndexPartBean index = filmService.getIndex();
        FilmIndexVo filmIndexVo = new FilmIndexVo();
        if (index != null){
            filmIndexVo.setData(index);
            filmIndexVo.setImgPre("");
            filmIndexVo.setMsg("");
            filmIndexVo.setNowPage("");
            filmIndexVo.setStatus(0);
            filmIndexVo.setTotalPage("");
        }else {
            filmIndexVo.setMsg("查询失败，无banner可加载");
            filmIndexVo.setStatus(1);
        }
        return filmIndexVo;
    }
    @RequestMapping("film/getConditionList")
    public FilmConditionVo getConditionList(Integer catId, Integer sourceId, Integer yearId){
        FilmConditionPartBean index = filmService.getConditionList(catId, sourceId, yearId);
        FilmConditionVo filmConditionBean = new FilmConditionVo();
        if (index != null){
            filmConditionBean.setData(index);
            filmConditionBean.setStatus(0);
            filmConditionBean.setImgPre("");
            filmConditionBean.setMsg("");
            filmConditionBean.setNowPage("");
            filmConditionBean.setTotalPage("");
        }else {
            filmConditionBean.setStatus(1);
            filmConditionBean.setMsg("查询失败，无条件可加载");
        }
        return filmConditionBean;
    }

    @RequestMapping(value = "film/getFilms",method=RequestMethod.GET)
    public FilmGetVo getFilms(FilmRequestInfo filmRequestInfo) {
        //数据校验没写
        Integer catId = filmRequestInfo.getCatId();
        Integer sourceId = filmRequestInfo.getSourceId();
        Integer yearId = filmRequestInfo.getYearId();
        Integer nowPage = filmRequestInfo.getNowPage();
        Integer pageSize = filmRequestInfo.getPageSize();
        Integer showType = filmRequestInfo.getShowType();
        if (catId == 99) catId=null;
        if (sourceId ==99)sourceId=null;
        if (yearId == 99) yearId =null;
        FilmGetVo filmGetVo = filmService.getHotFilms(false, 8, showType, sourceId, yearId,catId);
        return filmGetVo;
    }


    @RequestMapping(value = "film/films/{filmId}", method = RequestMethod.GET)
    public FilmDetailLv1VO getFilmDetail(@PathVariable(value = "filmId") int filmId, int searchType, String searchParam) {
        String filmId2 = String.valueOf(filmId);

        FilmDetailLv1VO fimlDetailVO = filmService.getFilmDeatils(filmId2);
        return fimlDetailVO;

    }
}
