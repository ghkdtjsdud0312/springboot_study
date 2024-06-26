package springboot.springbootbasic.repository;

import org.springframework.jdbc.datasource.DataSourceUtils;
import springboot.springbootbasic.domain.Member;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JdbcMemberRepository implements MemberRepository{

    private final DataSource dataSource;
    public JdbcMemberRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    // 추가 하기
    @Override
    public Member save(Member member) {
        String sql = "insert into member(name) values(?)";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null; // 결과를 받는 것
        try {
            conn = getConnection(); // getConnection()을 해서 커넥션을 가져온다.
            pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS); // prepareStatement에 sql을 넣는다. RETURN_GENERATED_KEYS은 DB에 인서트하면 id 값 얻는 것에 쓰임
            pstmt.setString(1, member.getName()); // setString에서 '파라미터 1번' 이라고 하면 쿼리문의 (?)값에 매칭된다. 그리고 member.getName()에 member.name으로 값을 넣는다.
            pstmt.executeUpdate(); // DB의 실제 쿼리가 날라간다.
            rs = pstmt.getGeneratedKeys(); // pstmt.getGeneratedKeys()를 꺼낼 수 있는데 옵션이 Statement.RETURN_GENERATED_KEYS랑 매칭되서 할 수 있다. 그리고 번호에 맞게 반환해준다.
            if (rs.next()) { // 값이 있으면 꺼낸다.
                member.setId(rs.getLong(1)); // getLong에서 값을 꺼내고 setId 해주면 된다.
            } else {
                throw new SQLException("id 조회 실패");
            }
            return member;
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, rs);
        }
    }

    // 조회 하기
    @Override
    public Optional<Member> findById(Long id) {
        String sql = "select * from member where id = ?";

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, id);

            rs = pstmt.executeQuery(); // 조회 할 때 executeQuery() 사용

            if(rs.next()) {
                Member member = new Member(); // member 값이 있으면 id,name은 set으로 받아옴
                member.setId(rs.getLong("id"));
                member.setName(rs.getString("name"));
                return Optional.of(member); // optional로 반환 해줌
            } else {
                return Optional.empty();
            }
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, rs);
        } }

    // 모든 회원 조회하기
    @Override
    public List<Member> findAll() {
        String sql = "select * from member";

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            List<Member> members = new ArrayList<>(); // list에 member를 담음

            while(rs.next()) {

                Member member = new Member();
                member.setId(rs.getLong("id"));
                member.setName(rs.getString("name"));
                members.add(member);
            }
            return members; // member 반환

        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, rs);
        }
    }

    // 회원 이름 조회 하기
    @Override
    public Optional<Member> findByName(String name) {
        String sql = "select * from member where name = ?";

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, name);
            rs = pstmt.executeQuery();

            if(rs.next()) {
                Member member = new Member(); // member get으로 받기
                member.setId(rs.getLong("id"));
                member.setName(rs.getString("name"));
                return Optional.of(member);
            }
            return Optional.empty();
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, rs);
        }
    }

    // 스프링 프레임워크를 쓸 때는 꼭 가져와야 한다.
    private Connection getConnection() {
        return DataSourceUtils.getConnection(dataSource); // DataSourceUtils를 통해서 dataSource를 가짐(데이터베이스 커넥션을 똑같은 걸 유지해주고 유지시켜줌)
    }

    private void close(Connection conn, PreparedStatement pstmt, ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (pstmt != null) {
                pstmt.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (conn != null) {
                close(conn);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 닫을 때는 DataSourceUtils를 통해서 release해줘야 한다.
    private void close(Connection conn) throws SQLException {
        DataSourceUtils.releaseConnection(conn, dataSource);
    } }
