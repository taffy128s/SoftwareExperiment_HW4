[Game]
MainActivity->
	�񭵼֡A���}window
MyWindow->
	���}typing thread��gaming thread
Typing->
	����known��unknown�����s�_��
	�A��hashmap��known��value�s�_��
	�Q��random���H����r
	�w�g��J�L���T���r�|�O���U��
	
	�C��ENTER���|�ˬdinput��value���S���ŦX
	�ŦX�N�|�G���åB�H����ܤU�@�Ӥ����ƪ��r
	���ŦX�O����ӥB�ϥΪ̥����ץ����~�i�H���U�@�Ӧr
	���r�|��_���զ�
	
	�p�G�����F�N�|����ʧ@�A�Boutput��output.txt
GameStage->
	�@�}�l�|��Ҧ��Ϥ����ɮ׸��m���B�z�n
	�M��Q��run�����n�l�B�y�|�W�U����
	�A�ھڤ��ƪ��ܤ���background�άO�n�l����
	(nowScore < 50��background�AnowScore <= 100���n�l)
	�����n�l�I��y�N�|���Ĺ���T���A�N�i�H���e�e�����C���F
[Reassembler]
	�}�@�ӤG���}�C�A��output.txt����Ū�i��
	�A�]�@�����h�j���F������L�X��
	
[�J�쪺�x��]
	�@�}�l�b�QGameStage��Typing�n����̪�F�ܦh�ɶ�
	��ӴN�Q��i�H����bMyWindow�̭��A�M���gs�Ƕityping
	�N�i�H�Q��gs��public method�ӥ[����
	
	���U�Ӧb�c��n�p��ƪ��]�O��F�ܦh�ɶ�
	��Ӥ~�o�{...�nsetLayout(null)�~�i�H��ʱƪ�
	�M��Ʀn������A�N�}�l��@�U��method
	��������x���a��N�hgoogle�A�j�P�W�S��������D
	�u�O�̫�Reassembler�b�B�z01-01.png�o��string�X�{�·�
	���O��ӵo�{�i�H��
	scanner.next().replaceAll("[^0-9]", " ").split(" ");
	��L�ܦ�01 01�N�i�H��idx�����X�Ӱ�!�}��

# 2016/4/11 �s�W���T�B���~����