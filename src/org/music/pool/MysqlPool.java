package org.music.pool;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.Date;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
public class MysqlPool {
	
	public static MysqlPool pool;
	
	static{
		pool = new MysqlPool("com.mysql.jdbc.Driver","jdbc:mysql://localhost:3306/cloud_music?characterEncoding=GBK","root","");
		try {
			pool.createConnections(4);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	public static void main(String[] args) {
		MysqlPool pool = new MysqlPool("com.mysql.jdbc.Driver","jdbc:mysql://localhost:3306/weibo","root","");
		try {
			pool.createConnections(4);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Connection conn = pool.getConnection();	
		try {
			String sql = "select * from user";
			PreparedStatement ps;
			ps = conn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()){
				System.out.println(rs.getString("area"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			pool.returnConnection(conn);
		}
		//long startTime=System.currentTimeMillis(); 
		//long endTime=System.currentTimeMillis(); 
		//System.out.println("��������ʱ�䣺 "+(endTime-startTime)+"ms"); 
	}
	
	private String jdbcDriver = "";//���ݿ�����
	private String dbUrl = "";//���ݿ�url
	private String dbUsername = "";//���ݿ��û���
	private String dbPassword = "";//���ݿ�����
	private String testTable = "user";
	private int initialConnectionsNum = 10;//���ӳس�ʼ������
	private int maxConnectionsNum = 50;//���ӳ����������
	private int incrementalConnections = 5;//ÿ�ζ�̬��ӵ�������
	private Vector<PooledConnection> connections = null;//������������ӳ��е����ӣ���ʼΪ��
	
	/*�޲ι��캯��*/
	public MysqlPool()
	{}
	
	/*�������Ĺ��캯��
	 * ��ʼ�����ݿ����������ݿ�url�����ݿ��û��������ݿ����롢���Ա�
	 * */
	public MysqlPool(String driver, String url, String name, String pass)
	{
		this.jdbcDriver = driver;
		this.dbUrl = url;
		this.dbUsername = name;
		this.dbPassword = pass;
		//this.testTable = table;
		try {
			this.createPool();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/*�������������ӳ�*/
	public synchronized void createPool() 
	throws InstantiationException, IllegalAccessException, 
	ClassNotFoundException, SQLException
	{
		/*ȷ�����ӳ�Ϊ����������Ѿ��������򱣴����ӵ�������Ϊ��
		 * */
		if (this.connections != null)
		{
			return ;
		}
		//������ʵ����
		Driver driver = (Driver)(Class.forName(this.jdbcDriver).newInstance());
		//ע��������
		DriverManager.registerDriver(driver);
		//�����������ӵ�����
		this.connections = new Vector<PooledConnection>();
		//�������ݿ�����
		this.createConnections(this.initialConnectionsNum);
	}
	
	/*�������������ݿ�����
	 * */
	private void createConnections (int num) throws SQLException
	{
		/*ѭ����������
		 * ��Ҫ���ȼ�鵱ǰ�������Ƿ��Ѿ��������ӳ����������
		 * */
		for (int i = 0; i < num; ++i)
		{
			//���
			if (this.connections.size() >= this.maxConnectionsNum)
			{
				return;
			}
			//��������
			this.connections.addElement
			(new PooledConnection(newConnection()));
		}
		
	}
	
	/*����,����һ�����ݿ�����*/
	private Connection newConnection() throws SQLException
	{
		/*��������*/
		Connection con = DriverManager.getConnection(this.dbUrl, 
				this.dbUsername, this.dbPassword);
		/*����ǵ�һ�δ������ӣ����������ӵ����ݿ����������������Ƿ�С��
		 * �������趨�����������*/
		if (this.connections.size() == 0)
		{
			DatabaseMetaData metadata = con.getMetaData();
			//�õ����ݿ����������
			int dbMaxConnectionsNum = metadata.getMaxConnections();
			//������ݿ������������С��������������趨�����ӳ����������
			if (dbMaxConnectionsNum > 0 
					&& this.maxConnectionsNum > dbMaxConnectionsNum)
			{
				this.maxConnectionsNum = dbMaxConnectionsNum;
			}
		}
		return con;
	}
	
	/*�������õ�һ����������
	 * */
	public synchronized Connection getConnection () 
	{
		Connection con = null;
		/*������ӳ��Ƿ��Ѿ�����*/
		if (this.connections == null)
		{
			return con;
		}
		//�õ�һ����������
		try {
			con = this.getFreeConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//���δ�ҵ��������ӣ�ѭ���ȴ������ң�֪���ҵ���������
		while(con == null)
		{
			this.wait(30);
			try {
				con = this.getFreeConnection();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return con;
	}
	
	
	/*�������õ�һ����������*/
	private Connection getFreeConnection() throws SQLException
	{
		Connection con = null;
		//����һ����������
		con = this.findFreeConnection();
		//���δ�ҵ��������ӣ��ͽ���һЩ�µ����ӣ��ٴβ���
		if (con == null)
		{
			this.createConnections(this.incrementalConnections);
			//�ٴβ���
			con = this.findFreeConnection();
		}
		return con;
	}
	
	
	/*�����������������в���һ����������
	 * �����е������У�����connections�У��ҵ�һ���������ӣ�
	 * ��������������Ƿ���ã��������������½������ӣ��滻ԭ��������*/
	private Connection findFreeConnection () throws SQLException
	{
		System.out.println("connection size "+this.connections.size());
		Connection con = null;
		for (int i = 0; i < this.connections.size(); ++i)
		{
			PooledConnection pol = (PooledConnection)this.connections.get(i);
			if (!pol.isBusy())
			{
				/*���������δ��ʹ�ã��򷵻�������Ӳ�����������ʹ�ñ�־*/
				con = pol.getCon();
				pol.setBusy(true);
				/*���������Ƿ����*/
				if (!this.testCon(con))
				{
					con = this.newConnection();
					pol.setCon(con);
				}
				break;
			}
		}
		return con;
	}
	
	/*���������������Ƿ����
	 * */
	private boolean testCon (Connection con)
	{
		boolean useable = true;
		try
		{
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select count(*) from " + this.testTable);
			rs.next();
		}
		catch(SQLException e)
		{
			/*�����׳��쳣�����Ӳ����ã��ر�*/
			useable = false;
			this.closeConnection(con);
		}
		return useable;
	}
	
	/*��������ʹ����ϵ����ӷŻ����ӳ���
	 * */
	public void returnConnection(Connection con)
	{
		/*ȷ�����ӳش���*/
		if (this.connections == null)
		{
			return ;
		}
		for (int i = 0; i < this.connections.size(); ++i)
		{
			PooledConnection pool = this.connections.get(i);
			//�ҵ���Ӧ���ӣ���������ʹ�ñ�־Ϊfalse
			if (con == pool.getCon())
			{
				pool.setBusy(false);
			}
		}
		
	}
	
	/*������ˢ�����ӳ��е�����*/
	public synchronized void refreshConneciontPool () throws SQLException
	{
		/*ȷ�����ӳش���*/
		if (this.connections == null)
		{
			return ;
		}
		for (int i = 0; i < this.connections.size(); ++i)
		{
			PooledConnection pool = this.connections.get(i);
			if (pool.isBusy())
			{
				this.wait(5000);
			}
			this.closeConnection(pool.getCon());
			pool.setCon(this.newConnection());
			pool.setBusy(false);
		}
	}

	/*�������ر����ӳ�*/
	public void closeConnectionPool()
	{
		/*ȷ�����ӳش���*/
		if (this.connections == null)
		{
			return ;
		}
		for (int i = 0; i < this.connections.size(); ++i)
		{
			PooledConnection pool = this.connections.get(i);
			if (pool.isBusy())
			{
				this.wait(5000);
			}
			this.closeConnection(pool.getCon());
			this.connections.remove(i);
		}
		this.connections = null;
	}
	
	/*��������ʱ�޿������ӣ�����ȴ����еȴ�m�룬����
	 * */
	private void wait(int mSecond)
	{
		try {
			Thread.sleep(mSecond);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * @return the jdbcDriver
	 */
	public String getJdbcDriver() {
		return jdbcDriver;
	}

	/**
	 * @param jdbcDriver the jdbcDriver to set
	 */
	public void setJdbcDriver(String jdbcDriver) {
		this.jdbcDriver = jdbcDriver;
	}

	/**
	 * @return the dbUrl
	 */
	public String getDbUrl() {
		return dbUrl;
	}

	/**
	 * @param dbUrl the dbUrl to set
	 */
	public void setDbUrl(String dbUrl) {
		this.dbUrl = dbUrl;
	}

	/**
	 * @return the dbUsername
	 */
	public String getDbUsername() {
		return dbUsername;
	}

	/**
	 * @param dbUsername the dbUsername to set
	 */
	public void setDbUsername(String dbUsername) {
		this.dbUsername = dbUsername;
	}

	/**
	 * @return the dbPassword
	 */
	public String getDbPassword() {
		return dbPassword;
	}

	/**
	 * @param dbPassword the dbPassword to set
	 */
	public void setDbPassword(String dbPassword) {
		this.dbPassword = dbPassword;
	}

	/**
	 * @return the testTable
	 */
	public String getTestTable() {
		return testTable;
	}

	/**
	 * @param testTable the testTable to set
	 */
	public void setTestTable(String testTable) {
		this.testTable = testTable;
	}

	/**
	 * @return the initialConnectionsNum
	 */
	public int getInitialConnectionsNum() {
		return initialConnectionsNum;
	}

	/**
	 * @param initialConnectionsNum the initialConnectionsNum to set
	 */
	public void setInitialConnectionsNum(int initialConnectionsNum) {
		this.initialConnectionsNum = initialConnectionsNum;
	}

	/**
	 * @return the maxConnectionsNum
	 */
	public int getMaxConnectionsNum() {
		return maxConnectionsNum;
	}

	/**
	 * @param maxConnectionsNum the maxConnectionsNum to set
	 */
	public void setMaxConnectionsNum(int maxConnectionsNum) {
		this.maxConnectionsNum = maxConnectionsNum;
	}

	/**
	 * @return the incrementalConnections
	 */
	public int getIncrementalConnections() {
		return incrementalConnections;
	}

	/**
	 * @param incrementalConnections the incrementalConnections to set
	 */
	public void setIncrementalConnections(int incrementalConnections) {
		this.incrementalConnections = incrementalConnections;
	}

	/**
	 * @return the connections
	 */
	public Vector<PooledConnection> getConnections() {
		return connections;
	}

	/**
	 * @param connections the connections to set
	 */
	public void setConnections(Vector<PooledConnection> connections) {
		this.connections = connections;
	}

	/*����������ʹ����ϣ��ر�����*/
	private void closeConnection (Connection con)
	{
		try
		{
			con.close();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	
	/*�ڲ�ʹ�õı������ݿ����ӵ���
	 * ������Ա���������ӡ��Ƿ�����ʹ��*/
	class PooledConnection
	{
		private Connection con = null;//����
		private boolean busy = false;//�Ƿ�����ʹ�ã�Ĭ��Ϊ��
		
		/*���캯��*/
		public PooledConnection(Connection con)
		{
			this.con = con;
		}

		/**
		 * @return the con
		 */
		public Connection getCon() {
			return con;
		}

		/**
		 * @param con the con to set
		 */
		public void setCon(Connection con) {
			this.con = con;
		}

		/**
		 * @return the busy
		 */
		public boolean isBusy() {
			return busy;
		}

		/**
		 * @param busy the busy to set
		 */
		public void setBusy(boolean busy) {
			this.busy = busy;
		}
	}

}

