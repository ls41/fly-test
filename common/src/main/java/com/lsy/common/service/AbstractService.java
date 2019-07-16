package com.lsy.common.service;

import com.lsy.common.domain.AbstractAuditingEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import javax.persistence.criteria.Predicate;
import javax.validation.constraints.NotBlank;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractService<T extends AbstractAuditingEntity> {

	public abstract JpaRepository<T, Long> getRepository();

	public abstract JpaSpecificationExecutor<T> getExecutor();

	private Specification<T> getSpecification(T t) {
		return (Specification<T>) (root, query, criteriaBuilder) -> {
			List<Predicate> predicates = new ArrayList<>();
			//此处的Field不包含父类中的private属性
			for (Field f : t.getClass().getDeclaredFields()) {
				try {
					//反射获取值
					f.setAccessible(true);
					//@NotBlank注解特殊处理
					if (f.isAnnotationPresent(NotBlank.class)
							&& t.getClass().getDeclaredMethod("get" + captureName(f.getName())).invoke(t) != null) {
						//如果有字段被@NotBlank注解那么将该字段做成模糊匹配
						predicates.add(
								criteriaBuilder.like(
										root.get(f.getName()).as(String.class)
										, "%" +
												t.getClass().getDeclaredMethod("get" + captureName(f.getName())).invoke(t).toString()
												+ "%"
								));
						continue;
					}
					//因为这里，要求所有的实体Entity字段都是包装类型不能是原始类型
					if (f.getType().equals(Boolean.class) && f.get(t) == null) {
						predicates.add(
								criteriaBuilder.or(
										criteriaBuilder.equal(root.get(f.getName()).as(Boolean.class), Boolean.TRUE),
										criteriaBuilder.equal(root.get(f.getName()).as(Boolean.class), Boolean.FALSE)
								)

						);
						continue;
					}
					//正常字段
					if (f.get(t) != null) {
						predicates.add(
								criteriaBuilder.equal(root.get(f.getName()).as(f.getType()), f.get(t))
						);
					}
				} catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}

			//与查询。查询参数尽量减少
			return query.where(criteriaBuilder.and(predicates.toArray(new Predicate[0]))).getRestriction();
		};
	}

	public T create(T t) {
		return this.getRepository().save(t);
	}

	public T update(T t) {
		return this.getRepository().save(t);
	}

	public void delete(T t) {
		this.getRepository().delete(t);
	}

	public void deleteById(Long id) {
		this.getRepository().deleteById(id);
	}

	//多条件查询
	public List<T> findByExample(T t) {
		return this.getExecutor().findAll(getSpecification(t));
	}

	//findById
	public T findById(Long id) {
		return this.getRepository().findById(id).get();
	}

	//首字母大写
	private String captureName(String name) {
		char[] cs = name.toCharArray();
		cs[0] -= 32;
		return String.valueOf(cs);

	}
}
