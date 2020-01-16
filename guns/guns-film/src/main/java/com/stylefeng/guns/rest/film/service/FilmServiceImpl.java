package com.stylefeng.guns.rest.film.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.stylefeng.guns.rest.bean.film.*;
import com.stylefeng.guns.rest.film.mapper.*;
import com.stylefeng.guns.rest.film.model.*;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
@Service(interfaceClass = FilmService.class)
public class FilmServiceImpl implements FilmService {
    @Autowired
    MtimeBannerTMapper mtimeBannerTMapper;
    @Autowired
    MtimeFilmTMapper mtimeFilmTMapper;
    @Autowired
    MtimeFilmInfoTMapper mtimeFilmInfoTMapper;

    @Autowired
    MtimeYearDictTMapper mtimeYearDictTMapper;
    @Autowired
    MtimeCatDictTMapper mtimeCatDictTMapper;
    @Autowired
    MtimeSourceDictTMapper mtimeSourceDictTMapper;
    @Autowired
    private MtimeCatDictTMapper catDictTMapper;
    @Autowired
    private MtimeYearDictTMapper yearDictTMapper;
    @Autowired
    private MtimeSourceDictTMapper sourceDictTMapper;
    @Autowired
    private MtimeFilmActorTMapper filmActorTMapper;
    @Autowired
    private MtimeActorTMapper actorTMapper;

    @Override
    public FilmIndexPartBean getIndex() {
        List<FilmBannerBean> bannerBeanList = new ArrayList<>();
        List<MtimeBannerT> list = mtimeBannerTMapper.select();
        FilmIndexPartBean filmIndexPartBean = new FilmIndexPartBean();
        for (MtimeBannerT mtimeBannerT : list) {
            FilmBannerBean filmBannerBean = new FilmBannerBean();
            filmBannerBean.setBannerAddress(mtimeBannerT.getBannerAddress());
            filmBannerBean.setBannerId(mtimeBannerT.getUuid().toString());
            filmBannerBean.setBannerUrl(mtimeBannerT.getBannerUrl());
            bannerBeanList.add(filmBannerBean);
        }
        filmIndexPartBean.setBanners(bannerBeanList);
        ArrayList<MtimeFilmT> list1 = mtimeFilmTMapper.selectOrderByFilmBoxOffice();
        List<FilmBoxRankingBean> filmBoxRankingBeanList1 = trans(list1);
        filmIndexPartBean.setBoxRanking(filmBoxRankingBeanList1);

        ArrayList<MtimeFilmT> list2 = mtimeFilmTMapper.selectOrderByFilmPresalenum();
        List<FilmBoxRankingBean> filmBoxRankingBeanList2 = trans(list2);
        filmIndexPartBean.setExpectRanking(filmBoxRankingBeanList2);

        ArrayList<MtimeFilmT> list3 = mtimeFilmTMapper.selectOrderByTop();
        List<FilmBoxRankingBean> filmBoxRankingBeanList3 = trans(list3);
        filmIndexPartBean.setTop100(filmBoxRankingBeanList3);

        ArrayList<MtimeFilmT> list4 = mtimeFilmTMapper.selectOrderByFilmStatus(1);
        List<FilmBoxRankingBean> filmBoxRankingBeanList4 = trans(list4);
        HotFilm hotFilms = new HotFilm();
        hotFilms.setFilmInfo(filmBoxRankingBeanList4);
        hotFilms.setFilmNum(filmBoxRankingBeanList4.size());
        hotFilms.setNowPage("");
        hotFilms.setTotalPage("");
        filmIndexPartBean.setHotFilms(hotFilms);

        ArrayList<MtimeFilmT> list5 = mtimeFilmTMapper.selectOrderByFilmStatus(2);
        List<FilmBoxRankingBean> filmBoxRankingBeanList5 = trans(list5);
        HotFilm soonFilms = new HotFilm();
        soonFilms.setFilmInfo(filmBoxRankingBeanList5);
        soonFilms.setFilmNum(filmBoxRankingBeanList5.size());
        soonFilms.setNowPage("");
        soonFilms.setTotalPage("");
        filmIndexPartBean.setSoonFilms(soonFilms);

        return filmIndexPartBean;
    }

    @Override
    public FilmConditionPartBean getConditionList(Integer catId, Integer sourceId, Integer yearId) {
        FilmConditionPartBean filmConditionPartBean = new FilmConditionPartBean();

        EntityWrapper<MtimeYearDictT> entityWrapper1 = new EntityWrapper();
        EntityWrapper<MtimeCatDictT> entityWrapper2 = new EntityWrapper();
        EntityWrapper<MtimeSourceDictT> entityWrapper3 = new EntityWrapper();
        entityWrapper1.isNotNull("UUID");
        entityWrapper2.isNotNull("UUID");
        entityWrapper3.isNotNull("UUID");
        if (catId != null && sourceId != null && yearId != null){
            List<MtimeYearDictT> mtimeYearDictTS = mtimeYearDictTMapper.selectList(entityWrapper1);
            List<FilmConditionYearBean> yearBeans = new ArrayList<>();
            for (MtimeYearDictT mtimeYearDictT : mtimeYearDictTS) {
                FilmConditionYearBean filmConditionYearBean = new FilmConditionYearBean();
                if (!mtimeYearDictT.getUuid().equals(yearId)){
                    filmConditionYearBean.setIsactive(false);
                    filmConditionYearBean.setYearId(mtimeYearDictT.getUuid().toString());
                    filmConditionYearBean.setYearName(mtimeYearDictT.getShowName());
                }else {
                    filmConditionYearBean.setIsactive(true);
                    filmConditionYearBean.setYearId(mtimeYearDictT.getUuid().toString());
                    filmConditionYearBean.setYearName(mtimeYearDictT.getShowName());
                }
                yearBeans.add(filmConditionYearBean);
            }

            List<MtimeCatDictT> mtimeCatDictTS = mtimeCatDictTMapper.selectList(entityWrapper2);
            List<FilmConditionCatInfoBean> catInfoBeans = new ArrayList<>();
            for (MtimeCatDictT mtimeCatDictT : mtimeCatDictTS) {
                FilmConditionCatInfoBean filmConditionCatInfoBean = new FilmConditionCatInfoBean();
                if (!mtimeCatDictT.getUuid().equals(catId)){
                    filmConditionCatInfoBean.setIsactive(false);
                    filmConditionCatInfoBean.setCatId(mtimeCatDictT.getUuid().toString());
                    filmConditionCatInfoBean.setCatName(mtimeCatDictT.getShowName());
                }else {
                    filmConditionCatInfoBean.setIsactive(true);
                    filmConditionCatInfoBean.setCatId(mtimeCatDictT.getUuid().toString());
                    filmConditionCatInfoBean.setCatName(mtimeCatDictT.getShowName());
                }
                catInfoBeans.add(filmConditionCatInfoBean);
            }

            List<MtimeSourceDictT> mtimeSourceDictTS = mtimeSourceDictTMapper.selectList(entityWrapper3);
            List<FilmConditionSourceBean> sourceBeans = new ArrayList<>();
            for (MtimeSourceDictT mtimeSourceDictT : mtimeSourceDictTS) {
                FilmConditionSourceBean filmConditionSourceBean = new FilmConditionSourceBean();
                if (!mtimeSourceDictT.getUuid().equals(sourceId)){
                    filmConditionSourceBean.setIsactive(false);
                    filmConditionSourceBean.setSourceId(mtimeSourceDictT.getUuid().toString());
                    filmConditionSourceBean.setSourceName(mtimeSourceDictT.getShowName());
                }else {
                    filmConditionSourceBean.setIsactive(true);
                    filmConditionSourceBean.setSourceId(mtimeSourceDictT.getUuid().toString());
                    filmConditionSourceBean.setSourceName(mtimeSourceDictT.getShowName());
                }
                sourceBeans.add(filmConditionSourceBean);
            }
            filmConditionPartBean.setCatInfo(catInfoBeans);
            filmConditionPartBean.setSourceInfo(sourceBeans);
            filmConditionPartBean.setYearInfo(yearBeans);
        }

        return filmConditionPartBean;
    }

    private List<FilmBoxRankingBean> trans(ArrayList<MtimeFilmT> list){
        List<FilmBoxRankingBean> filmBoxRankingBeanList = new ArrayList<>();
        for (MtimeFilmT mtimeFilmT : list) {
         //   MtimeFilmInfoT mtimeFilmInfoT = mtimeFilmInfoTMapper.selectById(mtimeFilmT.getUuid());
            FilmBoxRankingBean filmBoxRankingBean = new FilmBoxRankingBean();
            filmBoxRankingBean.setBoxNum(mtimeFilmT.getFilmBoxOffice());
            filmBoxRankingBean.setExpectNum(mtimeFilmT.getFilmPresalenum());
            filmBoxRankingBean.setFilmCats(mtimeFilmT.getFilmCats());
            filmBoxRankingBean.setFilmId(mtimeFilmT.getUuid().toString());
      //      filmBoxRankingBean.setFilmLength(mtimeFilmInfoT.getFilmLength().toString());
            filmBoxRankingBean.setFilmLength(null);
            filmBoxRankingBean.setFilmName(mtimeFilmT.getFilmName());
            filmBoxRankingBean.setFilmScore(mtimeFilmT.getFilmScore());
            filmBoxRankingBean.setFilmType(mtimeFilmT.getFilmType());
            filmBoxRankingBean.setImgAddress(mtimeFilmT.getImgAddress());
            filmBoxRankingBean.setScore(mtimeFilmT.getFilmScore());
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String format = simpleDateFormat.format(mtimeFilmT.getFilmTime());
            filmBoxRankingBean.setShowTime(format);
            filmBoxRankingBeanList.add(filmBoxRankingBean);
        }
        return filmBoxRankingBeanList;
    }

    @Override
    public FilmGetVo getHotFilms(boolean isLimit, Integer count, Integer showType, Integer sourceId, Integer yearId, Integer catId) {
        List<MtimeFilmT> mtimeFilmTList;
        FilmGetVo filmGetVo = new FilmGetVo();
      /*  if (sourceId == 99) {
            sourceId = null;
            if (yearId == 99) {
                yearId = null;
                if (catId == 99) {
                    catId = null;
                    mtimeFilmTList = filmTMapper.selectFilms(showType, sourceId, yearId,null);
                    List<FilmBoxRankingBean> filmBoxRankingBeanslist = convert2FilmBoxRankingBean1(mtimeFilmTList, catId);
                    filmGetVo.setData(filmBoxRankingBeanslist);
                    filmGetVo.setTotalPage(1);
                    filmGetVo.setImgPre(filmTMapper.selelctImgAddress(showType, sourceId, yearId, catId));
                    filmGetVo.setMsg("");
                    filmGetVo.setNowPage(1);
                    filmGetVo.setStatus(0);
                } else {
                    String catID1 = String.valueOf(catId);
                    String catID2 = "%#" + catID1 + "#%";
                    mtimeFilmTList = filmTMapper.selectFilms(showType, sourceId, yearId, catID2);
                    List<FilmBoxRankingBean> filmBoxRankingBeanslist = convert2FilmBoxRankingBean1(mtimeFilmTList, catId);
                    filmGetVo.setData(filmBoxRankingBeanslist);
                    filmGetVo.setTotalPage(1);
                    filmGetVo.setImgPre(filmTMapper.selelctImgAddress(showType, sourceId, yearId, catId));
                    filmGetVo.setMsg("");
                    filmGetVo.setNowPage(1);
                    filmGetVo.setStatus(0);
                }
            }
        }*/
        if (catId == null && sourceId == null && yearId ==null) {
            mtimeFilmTList = mtimeFilmTMapper.selectFilms(showType, sourceId, yearId,null);
            List<FilmBoxRankingBean> filmBoxRankingBeanslist = convert2FilmBoxRankingBean1(mtimeFilmTList, catId);
            filmGetVo.setData(filmBoxRankingBeanslist);
            filmGetVo.setTotalPage(1);
            filmGetVo.setImgPre("http://img.meetingshop.cn/");
            filmGetVo.setMsg("");
            filmGetVo.setNowPage(1);
            filmGetVo.setStatus(0);
        } else {
            String catID1 = String.valueOf(catId);
            String catID2 = "%#" + catID1 + "#%";
            mtimeFilmTList = mtimeFilmTMapper.selectFilms(showType, sourceId, yearId, catID2);
            List<FilmBoxRankingBean> filmBoxRankingBeanslist = convert2FilmBoxRankingBean1(mtimeFilmTList, catId);
            filmGetVo.setData(filmBoxRankingBeanslist);
            filmGetVo.setTotalPage(1);
            filmGetVo.setImgPre("http://img.meetingshop.cn/");
            filmGetVo.setMsg("");
            filmGetVo.setNowPage(1);
            filmGetVo.setStatus(0);
        }
        return  filmGetVo;
    }
    /**
     * 将查询到的listz转换为api VO的list
     * @param mtimeFilmTList
     * @param catId
     * @return
     */
    private List<FilmBoxRankingBean> convert2FilmBoxRankingBean1(List<MtimeFilmT> mtimeFilmTList,Integer catId) {
        List<FilmBoxRankingBean> filmBoxRankingBeanList = new ArrayList<>();
        if (CollectionUtils.sizeIsEmpty(mtimeFilmTList)){
            return filmBoxRankingBeanList;
        }
        for(MtimeFilmT film:mtimeFilmTList){
       /*String filmCats = film.getFilmCats();
       String[] filmCats2 = filmCats.split("#");
       String idinfo = String.valueOf(catId);*/
            FilmBoxRankingBean filmBoxRankingBean = new FilmBoxRankingBean();
      /* for (int i =1 ;i<filmCats2.length;i++){
            if (idinfo==filmCats2[i]) {*/
            filmBoxRankingBean.setBoxNum(film.getFilmBoxOffice());
            filmBoxRankingBean.setExpectNum(film.getFilmPresalenum());
            filmBoxRankingBean.setFilmCats("");
            filmBoxRankingBean.setFilmId(film.getUuid().toString());
            filmBoxRankingBean.setFilmLength("");
            filmBoxRankingBean.setFilmName(film.getFilmName());
            filmBoxRankingBean.setFilmScore(film.getFilmScore());
            filmBoxRankingBean.setFilmType(film.getFilmType());
            filmBoxRankingBean.setImgAddress(film.getImgAddress());
            filmBoxRankingBean.setScore(film.getFilmSource().toString());
            filmBoxRankingBean.setShowTime(film.getFilmTime().toString());
            filmBoxRankingBeanList.add(filmBoxRankingBean);
            /* }*/
        }
        return filmBoxRankingBeanList;
    }

    /**
     * 获取影片详情的部分
     * @param filmId
     * @return
     */
    @Override
    public FilmDetailLv1VO getFilmDeatils(String filmId) {
        FilmDetailLv1VO fimlDetailLv1= new FilmDetailLv1VO();
        Integer filmid= Integer.parseInt(filmId);
        //传入filmid去filminfo查出FilmDetailLv1里“data" 即 FilmDeatailLv2的部分数据
        EntityWrapper<MtimeFilmInfoT> ew1= new EntityWrapper<>();
        ew1.eq("film_id",filmId);
        List<MtimeFilmInfoT> filmInfoTList = mtimeFilmInfoTMapper.selectList(ew1);
        MtimeFilmInfoT  filmInfoT = filmInfoTList.get(0);
        FilmDetailLv2VO filmDetailLv2VO = new FilmDetailLv2VO();
        filmDetailLv2VO.setFilmEnName(filmInfoT.getFilmEnName());
        filmDetailLv2VO.setFilmId(filmInfoT.getFilmId());
        filmDetailLv2VO.setScore(filmInfoT.getFilmScore());
        filmDetailLv2VO.setScoreNum(String.valueOf(filmInfoT.getFilmScoreNum()));
        //去Filmv表里查询FilmDetaillv2除info4外剩下的数据  有的需要拼接
        EntityWrapper<MtimeFilmT> ew2 = new EntityWrapper<>();
        ew2.eq("UUID",filmId);
        List<MtimeFilmT> mtimeFilmTList = mtimeFilmTMapper.selectList(ew2);
        MtimeFilmT mtimeFilmT = mtimeFilmTList.get(0);
        filmDetailLv2VO.setFilmName(mtimeFilmT.getFilmName());
        filmDetailLv2VO.setImgAddress(mtimeFilmT.getImgAddress());
        filmDetailLv2VO.setTotalBox(String.valueOf(mtimeFilmT.getFilmBoxOffice()));
        //拼接info01,02,03
        //info1
        String filmCats = mtimeFilmT.getFilmCats();
        String[] catsid = filmCats.split("#");
        String catsid3 = "";
        for (int i = 1;i<catsid.length;i++){
            Integer x = Integer.parseInt(catsid[i]);
//            EntityWrapper<MtimeCatDictT> ew3 = new EntityWrapper<>();
            String catsid2 ;
//            ew3.eq("UUID",x);
            MtimeCatDictT catDictT = catDictTMapper.selectById(x);
            catsid2 = catDictT.getShowName();
            catsid3+=catsid2 + ",";
        }
        filmDetailLv2VO.setInfo01(catsid3);
        //拼接info2
        Integer filmArea = mtimeFilmT.getFilmArea();
        EntityWrapper<MtimeSourceDictT> ew4 = new EntityWrapper<>();
        ew4.eq("UUID",filmArea);
        List<MtimeSourceDictT> mtimeSourceDictTList= sourceDictTMapper.selectList(ew4);
        MtimeSourceDictT mtimeSourceDictT=mtimeSourceDictTList.get(0);
        String showName = mtimeSourceDictT.getShowName();
        String filmLength = String.valueOf(filmInfoT.getFilmLength());
        String info4 = showName + "/" + filmLength;
        filmDetailLv2VO.setInfo02(info4);
        //拼接info3
        Date filmTimeData = mtimeFilmT.getFilmTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String filmData = sdf.format(filmTimeData);
        String info3 = filmData + showName + "上映";
        filmDetailLv2VO.setInfo03(info3);
        //查询info4
        FilmInfo04 filmInfo04 = new FilmInfo04();
        filmInfo04.setBiopgraphy(filmInfoT.getBiography());
        filmInfo04.setFilmId(filmId);
        //查询里面的actors 即 ActorsaAndDirect 里的
        ActorAndDirectorVO actorAndDirectorVO = new ActorAndDirectorVO();
        EntityWrapper<MtimeFilmActorT> ew5=new EntityWrapper();
        ew5.eq("film_id",filmid);
        List<MtimeFilmActorT> mtimeFilmActorTList = filmActorTMapper.selectList(ew5);
        List<ActorsVO> actorsVOList = new ArrayList<>();
        //ActorsVO actorsVO = new ActorsVO();
        for(int i = 0;i<mtimeFilmActorTList.size();i++){
            ActorsVO actorsVO = new ActorsVO();
            MtimeFilmActorT mtimeFilmActorT = mtimeFilmActorTList.get(i);
            Integer actorId = mtimeFilmActorT.getActorId();
            EntityWrapper<MtimeActorT> ew6 = new EntityWrapper<>();
            ew6.eq("UUID",actorId);
            MtimeActorT mtimeActorT = actorTMapper.selectById(actorId);
            actorsVO.setDirectorName(mtimeActorT.getActorName());
            actorsVO.setImgAddress(mtimeActorT.getActorImg());
            actorsVO.setRoleName(mtimeFilmActorT.getRoleName());
            actorsVOList.add(actorsVO);
            actorAndDirectorVO.setActors(actorsVOList);
        }
        //查询director 用film表里的derector_id 的去actor表里去查
        Integer directorId = filmInfoT.getDirectorId();
       /* EntityWrapper<MtimeActorT> ew6 = new EntityWrapper<>();
        ew6.eq("UUID",directorId);*/
        MtimeActorT mtimeActorT = actorTMapper.selectById(directorId);
        DirectorVO directorVO = new DirectorVO();
        directorVO.setDirectorName(mtimeActorT.getActorName());
        directorVO.setImgAddress(mtimeActorT.getActorImg());
        directorVO.setRoleName("");
        actorAndDirectorVO.setDirector(directorVO);
        //查询imgvo
        String filmImgs = filmInfoT.getFilmImgs();
        String[] filmImgs2 = filmImgs.split(",");
        ImgVO imgVO = new ImgVO();
        imgVO.setImg01(filmImgs2[0]);
        imgVO.setImg02(filmImgs2[1]);
        imgVO.setImg03(filmImgs2[2]);
        imgVO.setImg04(filmImgs2[3]);
        imgVO.setMainImg(mtimeFilmT.getImgAddress());

        filmInfo04.setImgVO(imgVO);
        filmInfo04.setActors(actorAndDirectorVO);

        filmDetailLv2VO.setScore(filmInfoT.getFilmScore());
        filmDetailLv2VO.setScoreNum(String.valueOf(filmInfoT.getFilmScoreNum()));
        filmDetailLv2VO.setInfo04(filmInfo04);

        fimlDetailLv1.setData(filmDetailLv2VO);
        fimlDetailLv1.setImgPre(mtimeFilmT.getImgAddress());
        fimlDetailLv1.setMsg("");
        fimlDetailLv1.setNowPage("");
        fimlDetailLv1.setStatus(0);
        fimlDetailLv1.setTotalPage("");
        return fimlDetailLv1;

    }

    @Override
    public String getFilmNameById(Integer filmId) {
        return mtimeFilmTMapper.selectNameById(filmId);
    }

    @Override
    public String selectNameById(Integer filmId) {
        return mtimeFilmTMapper.selectNameById(filmId);
    }
}
