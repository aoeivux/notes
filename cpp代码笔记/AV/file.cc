#include <fstream>
#include <iostream>

using namespace std;

void test01()
{
	ofstream ofs;//写
	ifstream ifs;//读
	ofs.open("test.txt", ios::out);
	ofs << "姓名：张三" << endl;
	ofs << "性别：男" << endl;
	ofs << "年龄：18" << endl;

	ofs.close();
}

void test02()
{
	ifstream ifs;
	ifs.open("test.txt", ios::in);
	if(!ifs.is_open()){
		cout << "打开文件失败"<<endl;
	}

	return;
	
}

int main() {

	test01();

	test02();

	system("pause");

	return 0;
}