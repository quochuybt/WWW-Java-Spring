package iuh.fit.LeNguyenQuocHuy_Bai6_WebServlet.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DanhMuc {

    private int maDM;
    private String tenDanhMuc;
    private String nguoiQuanLy;
    private String ghiChu;
}
