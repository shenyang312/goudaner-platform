package com.goudaner.platform.base;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tk.mybatis.mapper.entity.Example;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by emmet on 17-3-29.
 * desc:
 */
public class SyMapperUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(SyMapperUtil.class);

    public static Example generateExample(Object obj, String... fieldStrs) {
        if (obj == null || ArrayUtils.isEmpty(fieldStrs)) return null;

        Class clazz = obj.getClass();
        Example example = new Example(clazz);
        Example.Criteria criteria = example.createCriteria();
        for (String fieldStr : fieldStrs) {
            try {
                Field field = clazz.getDeclaredField(fieldStr);
                field.setAccessible(true);
                Object value = field.get(obj);
                criteria.andEqualTo(fieldStr, value);
            } catch (NoSuchFieldException e) {
                LOGGER.error("字段" + fieldStr + "错误", e);
                return null;
            } catch (IllegalAccessException e) {
                LOGGER.error("字段" + fieldStr + "不可访问", e);
                return null;
            }
        }
        return example;
    }

    /**
     * 设置匹配字段数据
     *
     * @param t
     * @param criteria
     * @param fileds   where conditions
     * @author sunxiaolu
     * @since JDK 1.8
     */
    public static void setExampleCriteria(Class t, Example.Criteria criteria, Map<String, Object> params, String... fileds) throws SyException {
        if (ObjectUtils.equals(t, null) || ArrayUtils.isEmpty(fileds)) {
            return;
        }
        for (String filed : fileds) {
            if (StringUtils.isBlank(filed)) continue;
            for (Field f : t.getDeclaredFields()) {
                f.setAccessible(true);
                try {
                    String fieldName = f.getName();
                    if (!StringUtils.equals("serialVersionUID", fieldName)
                            && StringUtils.equals(fieldName, filed.split("@")[0])) {
//                        Object fieldValue = f.get(t);
                        Object fieldValue = params.get(filed);
                        String logic = "";
                        if (SyUtil.isNotEmpty(fieldValue)) {
                            String s = String.valueOf(fieldValue);
                            String[] split = s.split("\\|");
                            logic = s.split("!")[0];
                            if (split.length > 1) {
                                List<String> list = Arrays.asList(split);
                                params.put(fieldName, list);
                                fieldValue = params.get(fieldName);
                            }
                            /**
                             * xxx@startTime
                             * xxx@endTime
                             */
                            String[] split1 = filed.split("@");
                            if (split1.length > 0 && split1.length == 2) {
                                if (split1[1].contains("startTime")) {
                                    criteria.andGreaterThanOrEqualTo(fieldName, s);
                                } else if (split1[1].contains("endTime")) {
                                    criteria.andLessThanOrEqualTo(fieldName, s);
                                }
                                break;
                            }
/*
                            // @ 符分割的则范围查找
                            String[] split1 = s.split("@");
                            if (split1.length > 0 && split1.length == 2) {
                                criteria.andBetween(fieldName, split1[0], split1[1]);
                                break;
                            }*/
                        } else
                            break;
                        if (fieldValue.getClass().getName().contains("List")) {
                            criteria.andIn(fieldName, JSON.parseObject(JSON.toJSONString(fieldValue), List.class));
                        } else if (!ObjectUtils.equals(fieldValue, null) && SyUtil.isNotEmpty(logic)) {
                            switch (logic) {
                                case ">":
                                    criteria.andGreaterThan(fieldName, String.valueOf(fieldValue).split("!")[1]);
                                    break;
                                case "<":
                                    criteria.andLessThan(fieldName, String.valueOf(fieldValue).split("!")[1]);
                                    break;
                                case "<>":
                                    criteria.andNotEqualTo(fieldName, String.valueOf(fieldValue).split("!")[1]);
                                    break;
                                case ">=":
                                    criteria.andGreaterThanOrEqualTo(fieldName, String.valueOf(fieldValue).split("!")[1]);
                                    break;
                                case "<=":
                                    criteria.andLessThanOrEqualTo(fieldName, String.valueOf(fieldValue).split("!")[1]);
                                    break;
                                default:
                                    criteria.andEqualTo(fieldName, fieldValue);
                                    break;
                            }
                        }
                        break;
                    }
                } catch (IllegalArgumentException e) {
                    String message = e.getMessage();
                    LOGGER.error(message, e);
                    throw new SyException("200", "setExampleCriteria exception.");
                }
            }
        }
    }
}
