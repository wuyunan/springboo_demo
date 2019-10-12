package com.qingteng.spider.pipeline;

import com.qingteng.spider.entity.YieldCurve;
import com.qingteng.spider.repository.YieldCurveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.util.Map;

@Repository
public class Jin10Pipeline implements Pipeline {

    @Autowired
    protected YieldCurveRepository yieldCurveRepository;

    @Override
    public void process(ResultItems resultItems, Task task) {
        for (Map.Entry<String, Object> entry : resultItems.getAll().entrySet()) {
            if (entry.getKey().contains("yieldCurve")) {
                YieldCurve yieldCurve = (YieldCurve) entry.getValue();
                Specification<YieldCurve> specification = (Specification<YieldCurve>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.and(criteriaBuilder.equal(root.get("createDate"), yieldCurve.getCreateDate()));
                // if (newsRepository.findOne(specification) == null) {//检查链接是否已存在

                yieldCurveRepository.save(yieldCurve);
                //}
            }

        }
    }
}
