import xml.etree.ElementTree as ET
import xml.dom.minidom
base_tree = ET.parse('inverted_index.xml')
new_tree = ET.parse('input.xml')

base_root = base_tree.getroot()
new_root = new_tree.getroot()


for child in new_root:
    base_root.append(child)

base_tree.write('inverted_index.xml')