package group.Food_order_bachelor.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

import org.hibernate.annotations.JdbcType;
import org.hibernate.annotations.Type;
import org.hibernate.type.descriptor.jdbc.BinaryJdbcType;
import org.hibernate.type.descriptor.jdbc.VarbinaryJdbcType;
import org.springframework.web.multipart.MultipartFile;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="images")
public class Image {
    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name="id", insertable = false, updatable = false, nullable = false)
    private UUID id;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "file_type")
    private String fileType;

    @Column(name = "size")
    private long size;

    @Column(name = "system_name")
    private String systemName;

    @Lob
    @Column(name = "data")
    @JdbcType(VarbinaryJdbcType.class)
    private byte[] data;

    @OneToOne(mappedBy = "imageFood")
    private Food food = new Food();

    @OneToOne(mappedBy = "imageRestaurant")
    private Restaurant restaurant = new Restaurant();

}
