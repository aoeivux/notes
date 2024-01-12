#include <iostream>

// auto推导从左往右

int main(int argc, const char **argv) {
  // auto x = 1, y=1.2;
  int i = 5;
  auto &j = i;
  std::cout << j << std::endl;
  return 0;
}
