package bg.softUni.Countries.repository;

import bg.softUni.Countries.entity.Category;
import bg.softUni.Countries.entity.CategoryType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    Category findByName(CategoryType name);
}
