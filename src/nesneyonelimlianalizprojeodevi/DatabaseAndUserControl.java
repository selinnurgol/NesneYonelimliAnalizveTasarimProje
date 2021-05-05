package nesneyonelimlianalizprojeodevi;
import java.sql.*;


public class DatabaseAndUserControl{
	
	private  static DatabaseAndUserControl instance;
	private  static Connection conn;
	private String isim;
	private  String parola;
        
      // Builder Tasarım deseninin gerçeklenmesi
        public DatabaseAndUserControl(Kurucu kurucu){
            this.isim=kurucu.isim;
            this.parola=kurucu.parola;
            
        }

    private DatabaseAndUserControl() {
        
    }
        
        public String getIsim(){
            return isim;
        }
        
        public String getParola(){
            return parola;
        }
        
        public static class Kurucu{
            private String isim;
            private String parola;
            
            public Kurucu(){}
            
            public Kurucu isim(String isim){
                this.isim=isim;
                return this;
            }
            
            public Kurucu parola(String parola){
                this.parola=parola;
                return this;
            }
            
            public Company kurucu(){
                return new Company(this);
            }
        }
        
       

	public static Connection getConnection(){
            
           
		try{
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/javadb","postgres","Selin2718");
			System.out.println("Veritabani baglantisi saglandi");
			
		}catch(SQLException e) {
            System.err.println(" Hata bulundu "+e.getMessage());            
		}catch(ClassNotFoundException e){
			System.err.println(" Hata bulundu "+e.getMessage());
        }
		return conn;
	}
	
	public static DatabaseAndUserControl getInstance(){
		if(instance==null){
			synchronized (DatabaseAndUserControl.class) {
				if (instance==null) {
					instance=new DatabaseAndUserControl();
				}
			}
		}
		return instance;
	}
	
	public boolean Control(String _isim, String _parola) {
		try {
			
			Statement st = conn.createStatement();
			String sql = "SELECT * FROM users";
			ResultSet rs = st.executeQuery(sql);
			
			while(rs.next()){
				isim = rs.getString("user_name");
				parola = rs.getString("password");
				
			}
			
		}catch(Exception e) {
			System.out.println(e);
		}
		
		if(isim.equals(_isim)) {
			if(parola.equals(_parola)) {
				System.out.println("Sisteme basariyla giris yapildi!");
				return true;
			}
			else {
				System.out.println("Parolaniz yanlis lütfen tekrar deneyin!");
				return false;
			}
		}
		System.out.println("Kullanici bulunamadi!");
		return false;
        }
    }
