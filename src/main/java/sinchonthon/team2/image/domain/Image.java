package sinchonthon.team2.image.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Image {

    @Id @GeneratedValue
    @Column(name = "image_id")
    private Long id;

    // 업로드한 파일의 원본 이름
    @Column(name = "image_original_name")
    private String originalName;

    // UUID_원본 이름 으로 암호화하여 사용합니다. (이름 중복 방지)
    @Column(name = "image_conded_name")
    private String encodedName;

    /**
     * 정적 팩토리 메서드에서만 사용하는 기본 생성자.
     */
    private Image (String originalName, String encodedName) {
        this.originalName = originalName;
        this.encodedName = encodedName;
    }

    /**
     * 단일 공통 진입점으로 사용하는 정적 팩토리 메서드.
     * 이미지 등록시 호출합니다.
     */
    public static Image create(String originalName) {
        String encodedName = UUID.randomUUID().toString() + "_" + originalName;
        return new Image(originalName, encodedName);
    }
}

