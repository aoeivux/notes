#include <iostream>

namespace A {
namespace B {}
inline namespace C {
void foo() { std::cout << "foo" << std::endl; }
} // namespace C
} // namespace A

int main(int argc, const char **argv) {

	A::foo();
	return 0;
}