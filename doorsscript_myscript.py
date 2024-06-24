import requests
import json
import time
import urllib3
import os
# suppressing SSL-cert warnings
urllib3.disable_warnings(urllib3.exceptions.InsecureRequestWarning)

with open('config.txt') as f:
    config = f.read()

configuration = json.loads(config)

username = configuration['username']
password = configuration['password']
rest = configuration['instance'] + '/cb/rest'
swagger = configuration['instance'] + '/cb/api/v3'
sleeptime = 0.25

input('IMPORTANT NOTE: Please make sure you\'ve updated the config.txt file.\n__________\nABORT the script if you haven\'t!\nPress Enter/Return to continue...')

projectList = []
try:
 projectListTxt = open('_ProjectList_Created.txt').readlines()
 for entry in projectListTxt:
     projectList.append(entry.replace("\n", ""))
except:
 pass

trackerList = []
try:
 trackerListTxt = open('_TrackerList_Created.txt').readlines()
 for entry in trackerListTxt:
     trackerList.append(entry.replace("\n", ""))
except:
 pass
processedProjects = []
try:
 processedProjectsTxt = open('_ProjectList_Processed.txt').readlines()
 for entry in processedProjectsTxt:
     processedProjects.append(entry.replace("\n", ""))
except:
 pass

processedTrackers = []
try:
 processedTrackersTxt = open('_TrackerList_Processed.txt').readlines()
 for entry in processedTrackersTxt:
     processedTrackers.append(entry.replace("\n", ""))
except:
 pass

print('Preparing list of projects...')
if projectList == []:
    getProjects = requests.get(swagger + '/projects', auth=(username, password), verify=False)
    getProjectsJSON = (json.loads(getProjects.text))
    for project in getProjectsJSON:
        with open('_ProjectList.txt', 'a+') as updated:
            updated.write(f"{project['id']}\n")
        projectList.append(project['id'])
    os.rename('_ProjectList.txt', '_ProjectList_Created.txt')

print('Preparing list of trackers...')
if trackerList == []:
    for project in projectList:
        if project in processedProjects:
            pass
        else:
            getTrackers = requests.get(swagger + '/projects/' + str(project) + '/trackers', auth=(username, password), verify=False)
            getTrackersJSON = (json.loads(getTrackers.text))
            time.sleep(sleeptime)
            for tracker in getTrackersJSON:
                with open('_TrackerList.txt', 'a+') as updated:
                    updated.write(f"{tracker['id']}\n")
                trackerList.append(tracker['id'])
            with open('_ProjectList_Processed.txt', 'a+') as updated:
                updated.write(f"{project}\n")
            time.sleep(sleeptime)
    os.rename('_TrackerList.txt', '_TrackerList_Created.txt')

print('Processing trackers...')
numberOfTrackers = len(trackerList) - len(processedTrackers)
for tracker in trackerList:
    if tracker in processedTrackers:
        pass
    else:
        print(numberOfTrackers)
        deleteSetting = requests.delete(rest + '/tracker/' + str(tracker) + '/doors/settings?tracker_id='+ str(tracker), auth=(username, password), verify=False)
        time.sleep(sleeptime)
        if deleteSetting.status_code != 200:
            with open('_TrackerList_ERRORS.txt', 'a+') as updated:
                updated.write(f"{tracker} - ERROR: {deleteSetting.content}\n")

        else:
            with open('_TrackerList_Processed.txt', 'a+') as updated:
                updated.write(f"{tracker}\n")
        time.sleep(sleeptime)
        numberOfTrackers -= 1
