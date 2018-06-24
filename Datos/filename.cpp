#include <stdio.h> /* Defines FILENAME_MAX */
#include <string>
#include <iostream>
#include <fstream>
#include <sstream>

using namespace std;

#ifdef WINDOWS
	#include <direct.h>
	#define GetCurrentDir _getcwd
#else
	#include <unistd.h>
	#define GetCurrentDir getcwd
#endif

void find_and_replace(string& source, string const& find, string const& replace){
    for(string::size_type i = 0; (i = source.find(find, i)) != string::npos;)
    {
        source.replace(i, find.length(), replace);
        i += replace.length();
    }
}

int main(const int argc, const char *argv[]){
	char cCurrentPath[FILENAME_MAX];
	
	if(!GetCurrentDir(cCurrentPath, sizeof(cCurrentPath))){
		return 1;
	}
	
	cCurrentPath[sizeof(cCurrentPath) - 1] =  '\0';
	cout << "The current working directory is: " << cCurrentPath << endl<< endl;
		
	string currentPath(cCurrentPath);
	string inserciones = currentPath + "\\inserciones.sql";
	string result;
	
	cout << inserciones << endl;
	
	ifstream file(inserciones.c_str());
	stringstream buffer;
	
	buffer << file.rdbuf();
	string text = buffer.str();
	string target(argv[1]);
	
	cout << "string: " << text << endl << endl;
	find_and_replace(text,target,currentPath+"\\");
	cout << "replace: " << text << endl;
	
	file.close();
	
	ofstream outfile(inserciones.c_str());
	outfile.write(text.c_str(), text.size());
	outfile.close();
	
	
	system("pause");
	return 0;
}
