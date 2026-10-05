package iuh.fit.LeNguyenQuocHuy_Bai6_WebServlet.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TinTuc {

    private int maTinTuc;
    private String tieuDe;
    private String noiDungTinTuc;
    private String lienKet;
    private int maDM;
}
