package facade;

import java.util.List;

import mgr.Manageable;

// �Ļ�� �����̶�
// � ��� �κп� ���� �װ��� �����ְ� ���� �κи� �߻�ȭ�� ������
//�̽��� Ŭ������ ����

public interface DataEngineInterface<T extends Manageable> {
		// �� �Ŵ����� �����ϴ� �����͸� ���̺� �����ֱ� ���� 
		// �������� ������ �迭�� ��ȯ. �ʿ��� ���� ������ŭ �迭�� ��ȯ��
		int getColumnCount();
		String[] getColumnNames();
		// ���Ͽ��� Manager�� �����͸� ��� �о����
		void readAll(String filename);
		// Ű���忡 ��ġ�Ǵ� ���� ��� ã�� ����Ʈ�� ��ȯ
		List<T> search(String kwd);
		// UI ���̺��� �࿡ �ִ� �����͸� ��Ʈ�� �迭�� �޾ƿͼ� ���ο� ��ü �߰�
		void addNewItem(String[] uiTexts);
		// UI ���̺��� �࿡ �ִ� �����͸� ��Ʈ�� �迭�� �޾ƿͼ� �ش� ��ü ����
		void update(String[] uiTexts);
		// UI ���̺��� ���� ù��° �����͸� Ű�� �޾ƿ� �ش� ��ü�� ã�� ����
		void remove(String kwd);
}
