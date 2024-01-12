#include <iostream>
#include <limits>

int main(int argc, const char** argv) {
	std::cout << "std::numeric_limits<long long> max value is: " << std::numeric_limits<long long>::max() << std::endl;
	std::cout << "std::numeric_limits<long long> min value is: " << std::numeric_limits<long long>::min() << std::endl;

	char16_t utf16c = u'好';
	char32_t utf32c = U'好';

	char16_t utf16[] = u"你好世界";
	char32_t utf32[] = U"你好世界";
	// Windows常用字符类型
	// wchar_t
	return 0;
}