package coder.teamplte.models;

import javax.persistence.Entity;
import javax.persistence.*;

@Entity
public class Childcat {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   int id;
   String name;
   String image;

   @ManyToOne
   Subcat subcat;

   public Childcat() {
   };

   public Childcat(String name, String image, Subcat subcat) {
      this.name = name;
      this.image = image;
      this.subcat = subcat;
   };

   public int getId() {
      return this.id;
   }

   public void setId(int id) {
      this.id = id;
   }

   public String getName() {
      return this.name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getImage() {
      return this.image;
   }

   public void setImage(String image) {
      this.image = image;
   }

   public Subcat getSubcat() {
      return this.subcat;
   }

   public void setSubcat(Subcat subcat) {
      this.subcat = subcat;
   }

   @Override
    public String toString() {
        return "ChildCat{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", image='" + image + '\'' +
                '}';
    }

}
